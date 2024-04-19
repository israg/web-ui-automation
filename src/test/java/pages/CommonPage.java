package pages;

import utils.helper.Action;

public class CommonPage extends Action {

    public void verifyUrl(String url) {
        super.verifyUrl(url);
    }

    public void fill(String key, String text) {
        clearAndFillInput(key, text);
    }

    public void click(String key) {
        clickElement(findElement(key));
    }

    public void scrollToElement(String key) {
        super.scrollToElement(findElement(key));
    }

    public void scrollPageToDown(Integer index) {
        super.scrollPageToDown(index);
    }

    public void checkWithText(String key) {
        checkElementWithText(key);
    }
}
