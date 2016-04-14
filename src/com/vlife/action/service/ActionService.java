package com.vlife.action.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vlife.account.entity.Account;
import com.vlife.action.actions.ActionClose;
import com.vlife.action.entity.Action;
import com.vlife.database.service.DatabaseService;
import com.vlife.gm.entity.Species;

@Service
public class ActionService extends DatabaseService {

	public List<String> getAbleAction(Species species) {
		List<String> actionCodes = new ArrayList<String>();

		String actions = species.getActions();

		if (actions != null && !actions.equals("")) {
			for (String action : actions.split(",")) {
				actionCodes.add(action);
			}
		}

		return actionCodes;
	}

	public void addAction(Action action) throws Exception {
		this.save(action);
	}

	public Action getRunAction(Account account) throws Exception {
		String sql = "select * from action where account=? and status=?";
		return this.get(Action.class, sql, new Long[] { account.getId(), 0l });
	}

	public void actionClose(ActionClose close) throws Exception {
		Action running = close.getRunning();
		running.setStatus(1);
		running.setStopTime(new Date());
		close.closeAction();
		this.merge(running);
		this.merge(close.getAccount());
	}

	public List<Action> getActionCompleted(Account account) {
		String sql = "select * from action where account=? and status=?";
		return this.gets(Action.class, sql, new Long[] { account.getId(), 1l });
	}

	public Action getAction(Long actionId) throws Exception {
		String sql = "select * from action where id=?";
		return this.get(Action.class, sql, new Long[] { actionId });
	}

	public void deleteAction(Action action) throws Exception {
		action.setStatus(2);
		this.merge(action);
	}
}
