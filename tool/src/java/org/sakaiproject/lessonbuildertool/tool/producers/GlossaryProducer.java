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
import org.sakaiproject.lessonbuildertool.SimplePageExample;
import org.sakaiproject.lessonbuildertool.SimplePageGlossary;
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
public class GlossaryProducer implements ViewComponentProducer, NavigationCaseReporter, ViewParamsReporter {
	private SimplePageBean simplePageBean;
	private ShowPageProducer showPageProducer;
	private SimplePageToolDao simplePageToolDao;

	public MessageLocator messageLocator;
        public LocaleGetter localeGetter;                                                                                             

	public static final String VIEW_ID = "Glossary";

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

        	UIOutput.make(tofill,"glossary","Add Glossary");
        	// UIOutput.make(tofill,"term_label","Term");
        	// UIOutput.make(tofill,"desc_label","Description");
        	UIOutput.make(tofill,"category_label","Filter By Category : ");

        	String gCat = null;
        	//dropdown
        	List<SimplePageGlossary> listGlossary;
        	List<String> cate = new ArrayList();

        	listGlossary = simplePageToolDao.getAllGlossary();

        	for (SimplePageGlossary item : listGlossary){
        		cate.add(item.getCategory());
        	}
        	

        	String[] catArray = cate.toArray(new String[cate.size()]);

        	UIForm form = UIForm.make(tofill, "add-glossary");
        	UISelect cat = UISelect.make(form, "kategori", catArray, catArray, "#{simplePageBean.category}", "2");
        	UIInput.make(form, "term_glossary:", "#{simplePageBean.term}");
        	// term = simplePageBean.getTerm();
        	gCat = simplePageBean.getCategory();

        	UIInput.make(form, "desc_glossary:", "#{simplePageBean.desc}");
        	// UIInput.make(form, "category_glossary", "#{simplePageBean.category}");
        	UICommand.make(form, "submit","Add", "#{simplePageBean.processActionSubmit}");

        	if(gCat != null){
        		UIOutput.make(tofill,"term", gCat);

        		// List<SimplePageGlossary> listRGlossary;
        		listGlossary = simplePageToolDao.getGlossary(gCat);
        	}
        	else{
        		UIOutput.make(tofill,"term", "NONE");	
        	}

        	for (SimplePageGlossary item : listGlossary){
            	//Create a new <li> element
		            UIBranchContainer row = UIBranchContainer.make(tofill, "glossary-row:");
		            UIOutput.make(row, "id_glossary", Integer.toString(item.getid()));
		            UIOutput.make(row, "term_glossary", item.getTerm());
		            UIOutput.make(row, "desc_glossary", item.getDescription());
		            UIOutput.make(row, "category_glossary", item.getCategory());
        	}
        	
        	
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
		togo.add(new NavigationCase("success", new SimpleViewParameters(GlossaryProducer.VIEW_ID)));
		togo.add(new NavigationCase("cancel", new SimpleViewParameters(ShowPageProducer.VIEW_ID)));
		togo.add(new NavigationCase("failure", new SimpleViewParameters(ShowPageProducer.VIEW_ID)));

		return togo;
	}
}