package com.vlife.action.actions;

import java.util.Date;

import org.springframework.web.servlet.support.RequestContext;

import com.vlife.account.entity.Account;
import com.vlife.action.entity.Action;
import com.vlife.gm.service.GameService;

public abstract class ActionClose {

	public Account account;
	public GameService gameService;
	public RequestContext context;
	public Action running;

	public abstract void closeAction() throws Exception;

	public Integer pastTime(Integer fullTime) {
		return Math.min(fullTime, (int) (new Date().getTime() - running
				.getStartTime().getTime()) / (1000 * 60));
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public RequestContext getContext() {
		return context;
	}

	public void setContext(RequestContext context) {
		this.context = context;
	}

	public Action getRunning() {
		return running;
	}

	public void setRunning(Action running) {
		this.running = running;
	}
}
