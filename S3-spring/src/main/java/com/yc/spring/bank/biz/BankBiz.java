package com.yc.spring.bank.biz;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.spring.bank.dao.AccountDao;
import com.yc.spring.bank.dao.OprecordDao;

/**
 * 银行业务类
 * 开户：想Account表添加一条记录
 * 存取款：修改Account的余额，记录流水表
 * 	卡号 密码 余额
 * 转账：A 账号减少（取款）B账号增加
 * 查询
 */
@Service
@Transactional(rollbackFor= {IOException.class,SQLException.class})
public class BankBiz {

	@Autowired
	private AccountDao adao;
	@Autowired
	private OprecordDao odao;
	
	public void register(int id,String pwd,double money) {
		//省略参数校验
		adao.insert(id, pwd, money);
		odao.insert(id, money);
		
	}

	 @Transactional(rollbackFor= {BizException.class})
	public void save(int id,double money) throws BizException {
		adao.update(id, money);
		/* try { */
			if(money>999) {
				throw new BizException("存款金额不能为0");
			}
		/*} catch (BizException e) {
			//将该编译期异常转型为运行期异常
			throw new DataAccessResourceFailureException("存款金额不能为0",e);
		}*/
		odao.insert(id, money);
	}
	
	public void transfar(int id1,int id2,double money) throws BizException {
		save(id1, -money);
		save(id2, money);
	}
}
