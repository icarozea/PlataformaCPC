package com.plataforma.cpc.to;

public class HistoriaClinicaTo {

		private int idHistoria;
		private int idPaciente;
		private String codigo;
		
		public int getIdHistoria() {
			return idHistoria;
		}
		
		public void setIdHistoria(int idHistoria) {
			this.idHistoria = idHistoria;
		}
		
		public int getIdPaciente() {
			return idPaciente;
		}
		
		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}
		
		public String getCodigo() {
			return codigo;
		}
		
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}	
}