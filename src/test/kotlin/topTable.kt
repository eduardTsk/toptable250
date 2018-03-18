import org.junit.Test
import org.openqa.selenium.By

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import java.io.File

public class secTest {

    @Test
    public fun getPoret(){
        open("http://www.imdb.com/chart/top/?sort=ir,desc&mode=simple&page=1")
        val box_list = `$`(".lister-list")
        val sells = box_list.findAll("tr")

       File("top250.txt").bufferedWriter().use {out ->
            var idx: Int
            idx = 0
            while (idx <= sells.size - 1){

                val titleColumn = sells[idx].find(".titleColumn")
                val title_href = titleColumn.find("a")
                val title_href_title = title_href.getAttribute("title")
                val rateColumn = sells[idx].find(".ratingColumn.imdbRating")
                val rateTitle = rateColumn.find("strong").getAttribute("title")

                out.write(titleColumn.text() + "\n")
                out.write(rateTitle + "\n")
                out.write(title_href_title + "\n\n")

                idx++
            }
        }
    }
}