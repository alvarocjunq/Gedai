package br.com.gedai.bo;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaLista;
import br.com.gedai.dto.AtividadePDFDTO;
import br.com.gedai.enums.TipoListaEnum;
import br.com.gedai.mapper.DemandaListaMapper;
import br.com.gedai.utils.DateUtils;
import br.com.gedai.utils.FileUtils;
import br.com.gedai.utils.GenerateExcel;
import br.com.gedai.utils.StringUtils;


@Service
public class DemandaListaBO {
	
	@Autowired
	private DemandaListaMapper demandaListaMapper;
	
	@Autowired
	private DemandaListaAtividadeBO demandaListaAtividadeBO;
	
	@Autowired
	private DemandaTarefaBO demandaTarefaBO;

	public void insert(DemandaLista demandaLista) {
		demandaListaMapper.insert(demandaLista);
	}

	public void update(DemandaLista demandaLista) {
		demandaListaMapper.update(demandaLista);
	}

	public void delete(Integer idDemandaLista) {
		demandaListaMapper.delete(idDemandaLista);
	}

	public List<DemandaLista> obterPorDemanda(Integer idDemanda) {
		List<DemandaLista> lista = demandaListaMapper.obterPorDemanda(idDemanda);
		
		for(DemandaLista dl: lista)
			dl.setLstAtividades(demandaListaAtividadeBO.obterPorLista(dl.getId()));
		
		return lista;
	}
	
//	public void gerarPDF(Integer idDemanda, HttpServletResponse res){
////		List<TarefaPDFDTO> lstTarefas = demandaTarefaBO.obterTarefaPorDemandaPDF(idDemanda);
//		List<AtividadePDFDTO> lstAtividades = demandaListaAtividadeBO.obterAtividadePorDemandaPDF(idDemanda);
////		List<TarefaPDFDTO> lstTarefas = new ArrayList<TarefaPDFDTO>();
//		Map<String, Object> parametros = new HashMap<String, Object>();
////		parametros.put("tarefas", lstTarefas);
//		parametros.put("atividades", lstAtividades);
//		String nomeReportSaida = "Lista_Atividades";
//		PDFUtils.setImageParam(parametros, "logo", "logo-isban-fundo-cinza.jpg");
//		PDFUtils.gerar(parametros, lstAtividades, "vizualizarRelatorioDemanda", nomeReportSaida, res);
//	}
	
	public List<DemandaLista> obterProgressoRacional(Integer idDemanda){
		return demandaListaMapper.obterQtdAtividadePorLista(idDemanda);
	}
	
	public void gerarExcelAtividades(Integer idDemanda, HttpServletResponse res, HttpSession session){
		String arquivoFinal = StringUtils.concat("Atividades",DateUtils.getDataHoraAtualString("ddMMyyyy_HHmmss"));
		File fileFinal = FileUtils.copyFile("Lista_Atividade_Demanda.xlsx", arquivoFinal, session);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.getInputStream(arquivoFinal));
			List<AtividadePDFDTO> lstAtividades = demandaListaAtividadeBO.obterAtividadePorDemandaPDF(idDemanda);
			GenerateExcel.generateLines(workbook.getSheetAt(0), lstAtividades , 8, 2);
			GenerateExcel.gerarExcel(workbook, res, "Lista de Atividades", new Date());
			fileFinal.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer obterProgresso(Integer idDemanda){
		List<DemandaLista> lista = demandaListaMapper.obterQtdAtividadePorLista(idDemanda);
		double total = 0, feito = 0;
		
		for(DemandaLista dl: lista){
			TipoListaEnum tipo = TipoListaEnum.getEnum(dl.getNome());
			switch (tipo) {
			case FAZER:
				total+= dl.getQtd();
				break;
			case FAZENDO:
				total+= dl.getQtd();
				break;
			case FEITO:
				total+= dl.getQtd();
				feito += dl.getQtd();
				break;
			default:
				System.out.println("Tipo não cadastrado! DemandaLista: " + dl.getId());
				break;
			}
		}
		double d = ((feito / (total==0 ? 1: total)) * 100);
		return (int) d;
	}
}
