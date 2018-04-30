import scrapy
from insiderbot.items import *

class InsiderContentSpider(scrapy.Spider):
    name = 'insider_table'
    start_urls = [
        'http://openinsider.com/'
    ]

    def parse(self, response):
        item = Link()
        item['main_url'] = response.url
        # parse response and populate item as required
        url_1 = response.css('a[id=titlelink]::attr(href)')[0].extract()
        url_1 = response.urljoin(url_1)

        url_2 = response.css('a[id=titlelink]::attr(href)')[1].extract()
        url_2 = response.urljoin(url_2)

        url_3 = response.css('a[id=titlelink]::attr(href)')[2].extract()
        url_3 = response.urljoin(url_3)

        url_4 = response.css('a[id=titlelink]::attr(href)')[3].extract()
        url_4 = response.urljoin(url_4)

        url_5 = response.css('a[id=titlelink]::attr(href)')[4].extract()
        url_5 = response.urljoin(url_5)

        request1= scrapy.Request(url_1, callback=self.parse_cluster_buy)
        yield request1

        request2 = scrapy.Request(url_2, callback=self.parse_top_officer_purchases)
        yield request2

        request3 = scrapy.Request(url_3, callback=self.parse_latest_insider_purchases)
        yield request3

        request4 = scrapy.Request(url_4, callback=self.parse_latest_insider_sales)
        yield request4

        request5 = scrapy.Request(url_5, callback=self.parse_lastest_insider_trading)
        yield request5


    def parse_cluster_buy(self, response):
        #item = response.meta['item']
        columns1 = ['x', 'filling_date', 'trade_date', 'ticker', 'company', 'industry', 'ins', 'trade_type', 'share_price',
                    'shares_traded', 'shares_owned', 'own_chg', 'value_traded', 'one_day_ret', 'one_week_ret', 'one_month_ret',
                    'six_month_ret']

        # parse table1
        table1 = response.css('table.tinytable')[0]
        self.table_content = table1.css('tbody')
        for row in self.table_content.css('tr'):
            cluster_item = ClusterBuys()
            i = 0
            for col_name in columns1:
                try:
                    if not col_name == 'ticker':
                        cluster_item[col_name] = row.css('td')[i].css('*::text').extract()[0]
                    else:
                        cluster_item[col_name] = row.css('td')[i].css('*::text').extract()[1]
                except:
                    cluster_item[col_name] = ''
                i += 1

            cluster_item['dataset_name'] = 'LatestClusterBuys'
            yield cluster_item


    def parse_top_officer_purchases(self, response):
        columns2 = ['x', 'filling_date', 'trade_date', 'ticker', 'company', 'insider_name', 'insider_title', 'trade_type',
                    'share_price', 'shares_traded', 'shares_owned', 'own_chg', 'value_traded', 'one_day_ret', 'one_week_ret',
                    'one_month_ret', 'six_month_ret']

        # parse table2
        table2 = response.css('table.tinytable')[0]
        self.table_content = table2.css('tbody')
        for row in self.table_content.css('tr'):
            top_officer_purchases_item = TopOfficerPurchasesWeek()
            i = 0
            for col_name in columns2:
                try:
                    if not col_name == 'ticker':
                        top_officer_purchases_item[col_name] = row.css('td')[i].css('*::text').extract()[0]
                    else:
                        top_officer_purchases_item[col_name] = row.css('td')[i].css('*::text').extract()[1]
                except:
                    top_officer_purchases_item[col_name] = ''
                i += 1

                top_officer_purchases_item['dataset_name'] = 'TopOfficerPurchasesOfTheWeek'
            yield top_officer_purchases_item




    def parse_latest_insider_purchases(self, response):
        columns2 = ['x', 'filling_date', 'trade_date', 'ticker', 'company', 'insider_name', 'insider_title',
                    'trade_type',
                    'share_price', 'shares_traded', 'shares_owned', 'own_chg', 'value_traded', 'one_day_ret',
                    'one_week_ret',
                    'one_month_ret', 'six_month_ret']

        # parse table3
        table3 = response.css('table.tinytable')[0]
        self.table_content = table3.css('tbody')
        for row in self.table_content.css('tr'):
            latest_purchases_item = LatestInsiderPurchases()
            i = 0
            for col_name in columns2:
                try:
                    if not col_name == 'ticker':
                        latest_purchases_item[col_name] = row.css('td')[i].css('*::text').extract()[0]
                    else:
                        latest_purchases_item[col_name] = row.css('td')[i].css('*::text').extract()[1]
                except:
                    latest_purchases_item[col_name] = ''
                i += 1

            latest_purchases_item['dataset_name'] = 'LatestInsiderPurchases'
            yield latest_purchases_item

    def parse_latest_insider_sales(self, response):
        columns2 = ['x', 'filling_date', 'trade_date', 'ticker', 'company', 'insider_name', 'insider_title',
                    'trade_type',
                    'share_price', 'shares_traded', 'shares_owned', 'own_chg', 'value_traded', 'one_day_ret',
                    'one_week_ret',
                    'one_month_ret', 'six_month_ret']


        # parse table4
        table4 = response.css('table.tinytable')[0]
        self.table_content = table4.css('tbody')
        for row in self.table_content.css('tr'):
            latest_sales_item = LatestInsiderSales()
            i = 0
            for col_name in columns2:
                try:
                    if not col_name == 'ticker':
                        latest_sales_item[col_name] = row.css('td')[i].css('*::text').extract()[0]
                    else:
                        latest_sales_item[col_name] = row.css('td')[i].css('*::text').extract()[1]
                except:
                    latest_sales_item[col_name] = ''
                i += 1

            latest_sales_item['dataset_name'] = 'LatestInsiderSales'
            yield latest_sales_item

    def parse_lastest_insider_trading(self, response):
        columns2 = ['x', 'filling_date', 'trade_date', 'ticker', 'company', 'insider_name', 'insider_title',
                    'trade_type',
                    'share_price', 'shares_traded', 'shares_owned', 'own_chg', 'value_traded', 'one_day_ret',
                    'one_week_ret',
                    'one_month_ret', 'six_month_ret']

        # parse table5
        table5 = response.css('table.tinytable')[0]
        self.table_content = table5.css('tbody')
        for row in self.table_content.css('tr'):
            latest_trading_item = LatestInsiderTrading()
            i = 0
            for col_name in columns2:
                try:
                    if not col_name == 'ticker':
                        latest_trading_item[col_name] = row.css('td')[i].css('*::text').extract()[0]
                    else:
                        latest_trading_item[col_name] = row.css('td')[i].css('*::text').extract()[1]
                except:
                    latest_trading_item[col_name] = ''
                i += 1

            latest_trading_item['dataset_name'] = 'LatestInsiderTrading'
            yield latest_trading_item
