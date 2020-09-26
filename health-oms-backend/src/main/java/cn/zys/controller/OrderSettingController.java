package cn.zys.controller;

import cn.zys.common.MessageConst;
import cn.zys.common.OftenFinalMessage;
import cn.zys.entity.Result;
import cn.zys.exception.BusinessRuntimeException;
import cn.zys.pojo.OrderSetting;
import cn.zys.service.OrderSettingService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: road-health
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-09-26 15:57
 **/
@RestController
@RequestMapping("ordersetting")
@Slf4j
public class OrderSettingController {

    @Reference
    OrderSettingService orderSettingService;

    /**
     * 功能描述: <br>
     * 〈上传excl 批量插入数据〉
     *
     * @Param: [multipartFile]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/26 18:21
     */
    @RequestMapping("upload")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile) {
        log.info(":::文件大小：{} 文件名：{}", multipartFile.getSize(), multipartFile.getOriginalFilename());
        final String name = multipartFile.getOriginalFilename();
        if (StringUtils.isEmpty(name)) {
            return new Result(false, MessageConst.IMPORT_ORDERSETTING_FAIL + "没有可使用的文件");
        }
        Workbook workbook = null;
        try (final InputStream inputStream = multipartFile.getInputStream()) {
            if (name.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (name.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else {
                return new Result(false, "文件格式不正确，请检查重试");
            }
        } catch (IOException e) {
            log.error("", e);
            return new Result(false, "文件上传失败");
        }
        //创建插入数据的实体对象
        List<OrderSetting> orderSettings = new ArrayList<>();
        for (Sheet sheet : workbook) {
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                //创建一个处理行方法
                orderSettings.add(getOrderObj(sheet.getRow(i)));
            }
        }
        log.info("表格中的数据为{}", orderSettings);
        final Result result = orderSettingService.saveOrders(orderSettings);
        return result;
    }

    //处理行数据的方法
    public OrderSetting getOrderObj(Row row) {
        Date date = null;
        Integer number = null;
        Integer rowNum = null;
        try {
            if (row.getCell(0) != null) {
                date = row.getCell(0).getDateCellValue();
            }
            if (row.getCell(1) != null) {
                number = Double.valueOf(row.getCell(1).getNumericCellValue()).intValue();
            }
        } catch (IllegalStateException | NumberFormatException e) {
            log.error("", e);
            throw new BusinessRuntimeException(String.format("1数据出现问题，%s 的第 %d 行存在空", row.getSheet().getSheetName(), row.getRowNum() + 1));
        }
        // 判断是否缺少数据
        if (null == date || null == number) {
            throw new BusinessRuntimeException(String.format("2数据出现问题，%s 的第 %d 行存在空", row.getSheet().getSheetName(), row.getRowNum() + 1));
        }
        return new OrderSetting(date, number);
    }

    /**
     * 功能描述: <br>
     * 〈按月和年为条件查询〉
     *
     * @Param: [year, month]
     * @return: cn.zys.entity.Result
     * @Author: xiaozhang666
     * @Date: 2020/9/26 20:12
     */
    @RequestMapping("findAll")
    public Result findAll(@RequestParam Integer year, @RequestParam Integer month) {
        return orderSettingService.findAll(year, month);
    }

    @RequestMapping("editNumberByDate")
    public Result editNumberByDate(OrderSetting orderSetting) {
        log.info("参数检查:::{}", orderSetting);
        return orderSettingService.editNumberByDate(orderSetting);
    }
}
