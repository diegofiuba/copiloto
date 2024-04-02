/**
 * 
 */
package presentacion;

import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author diego
 *
 */
public class Formulario extends JPanel{

	private List<JTextField> camposDeTexto=new ArrayList<JTextField>();
	
	public Formulario(String[] nombreDeCampos,Class<?>[] tipoDeCampos) {
		this.setLayout(new GridLayout(0,2));
		
		for(int i=0;i<nombreDeCampos.length;i++) {
			
			JLabel etiqueta=new JLabel(nombreDeCampos[i]);
			add(etiqueta);
			
			if(tipoDeCampos[i]== String.class) {
			
				JTextField campoDeTexto = new JTextField();
				add(campoDeTexto);
				camposDeTexto.add(campoDeTexto);
			}else if(tipoDeCampos[i]== Long.class) {
			
				JFormattedTextField campoDeTexto = new JFormattedTextField(NumberFormat.getIntegerInstance());
				add(campoDeTexto);
				camposDeTexto.add(campoDeTexto);

			}else if(tipoDeCampos[i]== Float.class) {
			
				JFormattedTextField campoDeTexto = new JFormattedTextField(new DecimalFormat());
				add(campoDeTexto);
				camposDeTexto.add(campoDeTexto);

			}else if(tipoDeCampos[i]== LocalDate.class) {
				
			    DateFormat simpleDateFormat = SimpleDateFormat.getDateInstance();
				JFormattedTextField campoDeTexto = new JFormattedTextField(simpleDateFormat);			    
				LocalDate ld=LocalDate.now();

				Date date= Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

				campoDeTexto.setValue(date);
				add(campoDeTexto);
				camposDeTexto.add(campoDeTexto);

			}
		}
	}
	
	public void rellenarCon(String[] valores) {
		int i=0;
		for(JTextField campoDeTexto :camposDeTexto) {
			campoDeTexto.setText(valores[i]);
			i++;
		}
	}
	
	public void rellenarCon(String valor,int campo) {
		JTextField campoDeTexto = camposDeTexto.get(campo);
		campoDeTexto.setText(valor);
	}
	
	public String obtenerValorDelCampo(int campo) {
		JTextField campoDeTexto = camposDeTexto.get(campo);
		return campoDeTexto.getText();
	}
	
	public List<String> obtenerValores(){
	    List<String> valores=new ArrayList<String>();

		for(JTextField campoDeTexto :camposDeTexto) {
			valores.add(campoDeTexto.getText());
		}
		return valores;
	}
	
	public void limpiar() {
		for(JTextField campoDeTexto :camposDeTexto) {
			campoDeTexto.setText(null);
		}
	}
	
}
