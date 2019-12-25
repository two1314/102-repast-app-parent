package com.aaa.lee.app.controller;

  import com.aaa.lee.app.api.IRepastService;
        import com.aaa.lee.app.base.BaseController;
        import com.aaa.lee.app.base.ResultData;
        import com.aaa.lee.app.model.OmsOrder;
  import com.aaa.lee.app.status.StatusEnum;
  import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RestController;

  import java.util.List;

@RestController
@Api(value = "资金明细",tags ="资金明细接口" )
public class MoneyDetalController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     * 执行查询资金明细
     * @return
     */
    @PostMapping("/selectMoneyDetal")
    @ApiOperation(value = "查询资金" ,notes = "执行查询资金明细")
    public ResultData selectMoneyDetal(String token) {

        if (null != token) {
            //System.out.println(repastService.selectMoneyDetal(token));
            if (null != repastService.selectMoneyDetal(token)) {
                //说名登录成功
                return success(repastService.selectMoneyDetal(token), StatusEnum.SUCCESS.getMsg());
            }
        }
        return failed();
    }
}
