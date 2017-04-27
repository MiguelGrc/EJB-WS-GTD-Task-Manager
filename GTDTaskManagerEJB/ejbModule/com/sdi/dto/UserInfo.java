package com.sdi.dto;

import java.io.Serializable;

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private int numTareasCompletadas;
	private int numTareasCompletadasRetrasadas;
	private int numTareasPlanificadas;
	private int numTareasNoPlanificadas;
	
	public UserInfo(User user, int numTarCompl, int numTarComplRetrasadas,
							int numTarPlanificadas, int numTarNoPlanificadas){
		this.user = user;
		this.numTareasCompletadas = numTarCompl;
		this.numTareasCompletadasRetrasadas = numTarComplRetrasadas;
		this.numTareasPlanificadas = numTarPlanificadas;
		this.numTareasNoPlanificadas = numTarNoPlanificadas;
	}
	
	public User getUser(){
		return user;
	}

	public int getNumTareasCompletadas() {
		return numTareasCompletadas;
	}

	public int getNumTareasCompletadasRetrasadas() {
		return numTareasCompletadasRetrasadas;
	}

	public int getNumTareasPlanificadas() {
		return numTareasPlanificadas;
	}

	public int getNumTareasNoPlanificadas() {
		return numTareasNoPlanificadas;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setNumTareasCompletadas(int numTareasCompletadas) {
		this.numTareasCompletadas = numTareasCompletadas;
	}

	public void setNumTareasCompletadasRetrasadas(int numTareasCompletadasRetrasadas) {
		this.numTareasCompletadasRetrasadas = numTareasCompletadasRetrasadas;
	}

	public void setNumTareasPlanificadas(int numTareasPlanificadas) {
		this.numTareasPlanificadas = numTareasPlanificadas;
	}

	public void setNumTareasNoPlanificadas(int numTareasNoPlanificadas) {
		this.numTareasNoPlanificadas = numTareasNoPlanificadas;
	}

	
	
	 

}
