package IOStrategy;

import java.awt.Font;
import java.io.BufferedWriter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.validator.PublicClassValidator;

import com.orsoncharts.label.StandardCategoryItemLabelGenerator;
public class BarChart {
	public static void main(String[] args) {  
    CategoryDataset mDataset = GetDataset();          
    /* 
    //创建主题样式 
    StandardChartTheme mChartTheme = new StandardChartTheme("CN"); 
    //设置标题字体 
    mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20)); 
    //设置轴向字体 
    mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15)); 
    //设置图例字体 
    mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15)); 
    //应用主题样式 
    ChartFactory.setChartTheme(mChartTheme);         
    ///////////////以上主题设置必须位于创建图表函数之前才能生效////////////         
     */  
    JFreeChart mBarChart = ChartFactory.createBarChart(  
            "I/O time",  //图表标题  
            "Method and files",       //横轴（目录轴）标签  
            "Time",           //纵轴（数值轴）标签  
            mDataset,       //数据集  
            PlotOrientation.VERTICAL,   //图表方向  
            true,           //是否显示图例  
            true,           //是否生成提示工具  
            false);         //是否生成url连接  
    //图表标题设置  
    TextTitle mTextTitle = mBarChart.getTitle();  
    mTextTitle.setFont(new Font("黑体", Font.BOLD, 20));  
    //mBarChart.setTitle(new TextTitle("学校人员分布图",new Font("黑体", Font.BOLD, 20)));  
    //图表图例设置  
    LegendTitle mLegend = mBarChart.getLegend();  
    if(mLegend != null)  
        mLegend.setItemFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
    //mBarChart.getLegend().setItemFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
    //设置柱状图轴  
    CategoryPlot mPlot = (CategoryPlot)mBarChart.getPlot();  
      
    //x轴  
    CategoryAxis mDomainAxis = mPlot.getDomainAxis();  
    //设置x轴标题的字体  
    mDomainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));  
    //设置x轴坐标字体  
    mDomainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 15));  
    //y轴  
    ValueAxis mValueAxis = mPlot.getRangeAxis();  
    //设置y轴标题字体  
    mValueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));  
    //设置y轴坐标字体  
    mValueAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 15));  
    //柱体显示数值  
    BarRenderer mRenderer= new BarRenderer();  
   // mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
   // mRenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 10));  
    //mRenderer.setItemLabelsVisible(true);  
    mPlot.setRenderer(mRenderer);  
      
    ChartFrame mChartFrame = new ChartFrame("I/O time", mBarChart);  
    mChartFrame.pack();  
    mChartFrame.setVisible(true);  
}  
public static CategoryDataset GetDataset()  
{  
    DefaultCategoryDataset mDataset = new DefaultCategoryDataset();  
    mDataset.addValue(31966, "Stream", "Input1.txt");  
    mDataset.addValue(14019, "Reader", "Input1.txt");  
    mDataset.addValue(129173, "Files", "Input1.txt");  
    mDataset.addValue(499902, "Stream", "Input2.txt");  
    mDataset.addValue(1492216, "Reader", "Input2.txt");  
    mDataset.addValue(1416286, "Files", "Input2.txt");  
    mDataset.addValue(12663, "Stream", "Input3.txt");  
    mDataset.addValue(59081, "Reader", "Input3.txt");  
    mDataset.addValue(55134, "Files", "Input3.txt");  
    mDataset.addValue(17130, "Stream", "Input4.txt");  
    mDataset.addValue(65296, "Reader", "Input4.txt");  
    mDataset.addValue(60157, "Files", "Input4.txt");  
    mDataset.addValue(1082, "Stream", "Output1.txt");  
    mDataset.addValue(395, "Writer", "Output1.txt");  
    mDataset.addValue(865, "Files", "Output1.txt");  
    mDataset.addValue(1176, "Stream", "Output2.txt");  
    mDataset.addValue(412, "Writer", "Output2.txt");  
    mDataset.addValue(1000, "Files", "Output2.txt");  
    mDataset.addValue(1379, "Stream", "Output3.txt");  
    mDataset.addValue(603, "Writer", "Output3.txt");  
    mDataset.addValue(1160, "Files", "Output3.txt");  
    mDataset.addValue(1046, "Stream", "Output4.txt");  
    mDataset.addValue(338, "Writer", "Output4.txt");  
    mDataset.addValue(932, "Files", "Output4.txt");  
  
       
    return mDataset;  
}  
}
