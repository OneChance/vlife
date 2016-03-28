package com.vlife.account.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.vlife.account.entity.Account;
import com.vlife.database.service.DatabaseService;

@Service
public class AccountService extends DatabaseService {

	public String accountInputCheck(Account account) {

		if (account.getAccount() == null || account.getAccount().equals("")) {
			return "accountnull";
		}
		if (account.getPassword() == null || account.getPassword().equals("")) {
			return "passwordnull";
		}

		return "";
	}

	public String checkAccount(Account account) throws Exception {

		String inputRes = accountInputCheck(account);

		if (!inputRes.equals("")) {
			return inputRes;
		}

		Account accountDb = this.get(Account.class,
				"select * from account where account=?",
				new String[] { account.getAccount() });

		if (accountDb != null) {
			return "accountexist";
		} else {
			this.save(account);
		}

		return "";
	}

	public String checkLogin(Account account) throws Exception {

		String inputRes = accountInputCheck(account);

		if (!inputRes.equals("")) {
			return inputRes;
		}

		Account user_db = this.get(Account.class,
				"select * from account where account=? and password=?",
				new String[] { account.getAccount(), account.getPassword() });

		if (user_db == null) {
			return "accounterror";
		} else {
			account.setId(user_db.getId());
		}

		return "";
	}

	public Account getUser(String userid) throws NumberFormatException,
			Exception {
		Account account = this.get(Account.class, Long.parseLong(userid));
		return account;
	}

	public Account getLoginUser(HttpServletRequest request)
			throws NumberFormatException, Exception {
		Account account = (Account) request.getSession().getAttribute("loginu");
		return account;
	}
}
