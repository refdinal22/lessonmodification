package org.sakaiproject.lessonbuildertool.tool.producers;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import uk.org.ponder.messageutil.MessageLocator;
import uk.org.ponder.localeutil.LocaleGetter;                                                                                          
import uk.org.ponder.rsf.components.UIBranchContainer;
import uk.org.ponder.rsf.components.UIBoundBoolean;
import uk.org.ponder.rsf.components.UICommand;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIELBinding;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UISelect;
import uk.org.ponder.rsf.components.UIInternalLink;
import uk.org.ponder.rsf.components.UILink;
import uk.org.ponder.rsf.components.UIOutput;
import uk.org.ponder.rsf.components.decorators.UIFreeAttributeDecorator;
import uk.org.ponder.rsf.components.decorators.UITooltipDecorator;
import uk.org.ponder.rsf.evolvers.TextInputEvolver;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCase;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCaseReporter;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.SimpleViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParamsReporter;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.lessonbuildertool.SimplePage;

// modifikasi penjualan
import org.sakaiproject.lessonbuildertool.SimplePageProduk;
import org.sakaiproject.lessonbuildertool.SimplePageJenisProduk;
// modifikasi penjualan

import org.sakaiproject.lessonbuildertool.SimplePageImpl;
import org.sakaiproject.lessonbuildertool.SimplePageItem;
import org.sakaiproject.lessonbuildertool.tool.beans.SimplePageBean;
import org.sakaiproject.lessonbuildertool.model.SimplePageToolDao;
import org.sakaiproject.lessonbuildertool.tool.view.GeneralViewParameters;
import org.sakaiproject.tool.cover.SessionManager;

/**
 * Uses an FCK editor to edit blocks of text.
 * 
 * @author Joshua Ryan josh@asu.edu alt^I
 * @author Eric Jeney <jeney@rutgers.edu>
 */
@Slf4j
public class DetailProdukProducer implements ViewComponentProducer, NavigationCaseReporter, ViewParamsReporter {
	private SimplePageBean simplePageBean;
	private ShowPageProducer showPageProducer;
	private SimplePageToolDao simplePageToolDao;

	public MessageLocator messageLocator;
        public LocaleGetter localeGetter;                                                                                             

	public static final String VIEW_ID = "DetailProduk";

	public String getViewID() {
		return VIEW_ID;
	}

	public void fillComponents(UIContainer tofill, ViewParameters viewparams, ComponentChecker checker) {

		GeneralViewParameters gparams = (GeneralViewParameters) viewparams;

		if (gparams.getSendingPage() != -1) {
			// will fail if page not in this site
			// security then depends upon making sure that we only deal with this page
			try {
				simplePageBean.updatePageObject(gparams.getSendingPage());
			} catch (Exception e) {
				log.info("EditPage permission exception " + e);
				return;
			}
		}

		UIOutput.make(tofill, "html").decorate(new UIFreeAttributeDecorator("lang", localeGetter.get().
        	getLanguage())).decorate(new UIFreeAttributeDecorator("xml:lang", localeGetter.get().getLanguage()));  

		int kodeProduk;
		kodeProduk = Integer.valueOf(gparams.getProdukId());

		SimplePageProduk detailProduk = simplePageToolDao.getDetailProduk(kodeProduk);
        
        String[] kodeArray = new String[]{gparams.getProdukId()};

   		UIOutput.make(tofill, "produk_name", messageLocator.getMessage("simplepage.produkname")); 
   		UIOutput.make(tofill, "produk_stok", messageLocator.getMessage("simplepage.produkstok"));    
   		UIOutput.make(tofill, "produk_desc", messageLocator.getMessage("simplepage.produkdesc"));    

   		UIOutput.make(tofill, "detailNama", detailProduk.getNamaProduk()); 
   		UIOutput.make(tofill, "detailStok", Integer.toString(detailProduk.getStok()));    
   		UIOutput.make(tofill, "detailDesc", detailProduk.getDeskripsi());    

   		UIOutput.make(tofill, "kode_name", "Kode Produk"); 
   		UIForm form = UIForm.make(tofill, "add-produk");
   		UISelect cat = UISelect.make(form, "kode_produk", kodeArray, kodeArray, "#{simplePageBean.kode}", "2");
   		UIOutput.make(tofill, "produk_qty", messageLocator.getMessage("simplepage.produkqty"));    

   		UIOutput.make(tofill, "input_kode", gparams.getProdukId());    

   		// UIInput.make(form, "input_kode", "#{simplePageBean.kode}");    
        UIInput.make(form, "input_qty", "#{simplePageBean.qty}");
    	simplePageBean.setKode(gparams.getProdukId());
        UICommand.make(form, "submit", "Beli" ,"#{simplePageBean.tambahPenjualan}");
         	
	}

	private void createStandardLink(String viewID, UIContainer tofill, String ID, String message, SimpleViewParameters params){
		params.viewID = viewID;
		UILink link = UIInternalLink.make(tofill, ID, message, params);
	}
	public void setShowPageProducer(ShowPageProducer showPageProducer) {
		this.showPageProducer = showPageProducer;
	}

	public void setSimplePageBean(SimplePageBean simplePageBean) {
		this.simplePageBean = simplePageBean;
	}

	public void setSimplePageToolDao(SimplePageToolDao s) {
		simplePageToolDao = s;
	}

	public ViewParameters getViewParameters() {
		return new GeneralViewParameters();
	}

	public List reportNavigationCases() {
		List<NavigationCase> togo = new ArrayList<NavigationCase>();
		togo.add(new NavigationCase(null, new SimpleViewParameters(ShowPageProducer.VIEW_ID)));
		togo.add(new NavigationCase("success", new SimpleViewParameters(ShowProdukProducer.VIEW_ID)));
		togo.add(new NavigationCase("cancel", new SimpleViewParameters(ShowPageProducer.VIEW_ID)));
		togo.add(new NavigationCase("failure", new SimpleViewParameters(DetailProdukProducer.VIEW_ID)));

		return togo;
	}
}