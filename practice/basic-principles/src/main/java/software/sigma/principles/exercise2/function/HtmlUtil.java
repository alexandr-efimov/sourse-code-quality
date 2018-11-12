package software.sigma.principles.exercise2.function;


import software.sigma.principles.exercise2.function.model.PageCrawlerImpl;
import software.sigma.principles.exercise2.function.model.PageData;
import software.sigma.principles.exercise2.function.model.PathParser;
import software.sigma.principles.exercise2.function.model.SuiteResponder;
import software.sigma.principles.exercise2.function.model.WikiPage;
import software.sigma.principles.exercise2.function.model.WikiPagePath;

// TODO refactor it
public class HtmlUtil {
  public static String testableHtml(
          PageData pageData,
          boolean includeSuiteSetup
  ) throws Exception {
    WikiPage wikiPage = pageData.getWikiPage();
    StringBuffer buffer = new StringBuffer();
    if (pageData.hasAttribute("Test")) {
      if (includeSuiteSetup) {
        WikiPage suiteSetup =
                PageCrawlerImpl.getInheritedPage(
                        SuiteResponder.SUITE_SETUP_NAME, wikiPage
                );
        if (suiteSetup != null)
        {
          WikiPagePath pagePath =
                  suiteSetup.getPageCrawler().getFullPath(suiteSetup);
          String pagePathName = PathParser.render(pagePath);
//        System.out.println("Add include setup");
          buffer.append("!include -setup .")
                  .append(pagePathName)

                  .append("\n");
        }
      }
      WikiPage setup =
              PageCrawlerImpl.getInheritedPage("SetUp", wikiPage);
      if (setup != null) {
//        System.out.println("Setup not null");
        WikiPagePath setupPath =
                wikiPage.getPageCrawler()
                        .getFullPath(setup);
//        System.out.println("Wiki page path: " + wikiPage);
        String setupPathName = PathParser.render(setupPath);
//        System.out.println("Add include setup");
        buffer.append("!include -setup .")
                .append(setupPathName)
                .append("\n");
      }
    }
    buffer.append(pageData.getContent());
    if (pageData.hasAttribute("Test")) {
      WikiPage teardown =
              PageCrawlerImpl.getInheritedPage("TearDown", wikiPage);
      if (teardown != null) {
        WikiPagePath tearDownPath =
                wikiPage.getPageCrawler().getFullPath(teardown);
        String tearDownPathName = PathParser.render(tearDownPath);
        //        System.out.println("Add include teardown");
        buffer.append("\n")
                .append("!include -teardown .")
                .append(tearDownPathName)
                .append("\n");
      }

      if (includeSuiteSetup) {
        WikiPage suiteTeardown =
                PageCrawlerImpl.getInheritedPage(
                        SuiteResponder.SUITE_TEARDOWN_NAME,
                        wikiPage
                );
        if (suiteTeardown != null) {
          WikiPagePath pagePath =
                  suiteTeardown.getPageCrawler().getFullPath (suiteTeardown);
          String pagePathName = PathParser.render(pagePath);
          //        System.out.println("Add include teardown");
          buffer.append("!include -teardown .")
                  .append(pagePathName)
                  .append("\n");
        }
      }
    }
    pageData.setContent(buffer.toString());
    return pageData.getHtml();
  }
}