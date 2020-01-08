package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.mapper.MemberReceiveAddressMapper;
import com.aaa.lee.app.model.MemberReceiveAddress;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberReceiveAddressService extends BaseService<MemberReceiveAddress> {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;

    @Override
    public Mapper<MemberReceiveAddress> getMapper() {
        return memberReceiveAddressMapper;
    }

    /**
     * 查询会员所有的收货地址
     * @return
     */
    public ResultData<List<MemberReceiveAddress>> selectAddress(String token){

        ResultData<List<MemberReceiveAddress>> resultData = new ResultData<>();
        //查询数据库中所有的会员地址信息
        List<MemberReceiveAddress> addressList = memberReceiveAddressMapper.selectAll();
        //判断数据库中是否有数据
        // 查询成功
        if (addressList.size() > 0){
            resultData.setCode(StatusEnum.SUCCESS.getCode());
            resultData.setMsg(StatusEnum.SUCCESS.getMsg());
            resultData.setData(addressList);
        //查询失败
        }else {
            resultData.setCode(StatusEnum.FAILED.getCode());
            resultData.setMsg(StatusEnum.FAILED.getMsg());
        }
        return resultData;
    }

    /**
     * 保存或修改会员收货地址
     * @return
     */
    public Map<String,Object> saveOrUpdateAddress(MemberReceiveAddress memberReceiveAddress,String token){

        Map<String, Object> resultMap = new HashMap<>();

            //如果id为空的话
            if ("".equals(memberReceiveAddress.getId()) || null == memberReceiveAddress.getId()){
                //保存会员地址
                int count = memberReceiveAddressMapper.insert(memberReceiveAddress);
                if (count == 1){
                    resultMap.put("code",StatusEnum.SUCCESS.getCode());
                    resultMap.put("msg",StatusEnum.SUCCESS.getMsg());
                    resultMap.put("data",count);
                }else {
                    resultMap.put("code",StatusEnum.FAILED.getCode());
                    resultMap.put("msg",StatusEnum.FAILED.getMsg());
                    resultMap.put("data",count);
                }
        }else {
            //修改会员地址
            int count = memberReceiveAddressMapper.updateByPrimaryKey(memberReceiveAddress);
            if (count == 1){
                resultMap.put("code",StatusEnum.SUCCESS.getCode());
                resultMap.put("msg",StatusEnum.SUCCESS.getMsg());
                resultMap.put("data",count);
            }else {
                resultMap.put("code",StatusEnum.FAILED.getCode());
                resultMap.put("msg",StatusEnum.FAILED.getMsg());
                resultMap.put("data",count);
            }
        }
        return resultMap;
    }

    /**
     * 根据id查询会员收货地址
     * @param id
     * @return
     */
    public Map<String,Object> findAddressByMemberId(Long id,String token){
        Map<String, Object> resultMap = new HashMap<>();
        MemberReceiveAddress memberReceiveAddress = memberReceiveAddressMapper.selectByPrimaryKey(id);
        if (StringUtil.isNotEmpty(memberReceiveAddress.getId().toString())){
            resultMap.put("code",StatusEnum.EXIST.getCode());
            resultMap.put("msg",StatusEnum.EXIST.getMsg());
            resultMap.put("data",memberReceiveAddress);

        }else {
            resultMap.put("code",StatusEnum.NOT_EXIST.getCode());
            resultMap.put("msg",StatusEnum.NOT_EXIST.getMsg());
        }
        return resultMap;
    }

    /**
     * 根据id删除会员收货地址
     * @param id
     * @return
     */
    public Map<String,Object> deleteAddress(Long id,String token){
        Map<String, Object> resultMap = new HashMap<>();
        if (null != id){
            int count = memberReceiveAddressMapper.deleteAddress(id);
            if (count != 1){
                resultMap.put("code",StatusEnum.FAILED.getCode());
                resultMap.put("msg",StatusEnum.FAILED.getMsg());
                return resultMap;
            }
        }
        return resultMap;
    }


    /**
     * 根据会员id修改会员地址的是否为默认地址
     *       0  设置为不默认
     *       1  设置为默认
     * @param id
     * @return
     */
    public Map<String,Object> updateAddressStatus(Long id,Long memberId,String token){
        Map<String, Object> resultMap = new HashMap<>();
        if (null != memberId){
            int i = memberReceiveAddressMapper.updateAllAddressStatus(memberId);
            if (i > 0){
                int count = memberReceiveAddressMapper.updateAddressStatus(id);
                    if (count != 1){
                    resultMap.put("code",StatusEnum.FAILED.getCode());
                    resultMap.put("msg",StatusEnum.FAILED.getMsg());
                        return resultMap;
                }
            }
        }
        return resultMap;
    }


}
