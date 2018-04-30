# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class ClusterBuys(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    x = scrapy.Field()
    filling_date = scrapy.Field()
    trade_date = scrapy.Field()
    ticker = scrapy.Field()
    company = scrapy.Field()
    industry = scrapy.Field()
    ins = scrapy.Field()
    trade_type = scrapy.Field()
    share_price = scrapy.Field()
    shares_traded = scrapy.Field()
    shares_owned = scrapy.Field()
    own_chg = scrapy.Field()
    value_traded = scrapy.Field()
    one_day_ret = scrapy.Field()
    one_week_ret = scrapy.Field()
    one_month_ret = scrapy.Field()
    six_month_ret = scrapy.Field()
    dataset_name = scrapy.Field()


class TopOfficerPurchasesWeek(scrapy.Item):
    x = scrapy.Field()
    filling_date = scrapy.Field()
    trade_date = scrapy.Field()
    ticker = scrapy.Field()
    company = scrapy.Field()
    insider_name = scrapy.Field()
    insider_title = scrapy.Field()
    trade_type = scrapy.Field()
    share_price = scrapy.Field()
    shares_traded = scrapy.Field()
    shares_owned = scrapy.Field()
    own_chg = scrapy.Field()
    value_traded = scrapy.Field()
    one_day_ret = scrapy.Field()
    one_week_ret = scrapy.Field()
    one_month_ret = scrapy.Field()
    six_month_ret = scrapy.Field()
    dataset_name = scrapy.Field()


class LatestInsiderPurchases(scrapy.Item):
    x = scrapy.Field()
    filling_date = scrapy.Field()
    trade_date = scrapy.Field()
    ticker = scrapy.Field()
    company = scrapy.Field()
    insider_name = scrapy.Field()
    insider_title = scrapy.Field()
    trade_type = scrapy.Field()
    share_price = scrapy.Field()
    shares_traded = scrapy.Field()
    shares_owned = scrapy.Field()
    own_chg = scrapy.Field()
    value_traded = scrapy.Field()
    one_day_ret = scrapy.Field()
    one_week_ret = scrapy.Field()
    one_month_ret = scrapy.Field()
    six_month_ret = scrapy.Field()
    dataset_name = scrapy.Field()

class LatestInsiderSales(scrapy.Item):
    x = scrapy.Field()
    filling_date = scrapy.Field()
    trade_date = scrapy.Field()
    ticker = scrapy.Field()
    company = scrapy.Field()
    insider_name = scrapy.Field()
    insider_title = scrapy.Field()
    trade_type = scrapy.Field()
    share_price = scrapy.Field()
    shares_traded = scrapy.Field()
    shares_owned = scrapy.Field()
    own_chg = scrapy.Field()
    value_traded = scrapy.Field()
    one_day_ret = scrapy.Field()
    one_week_ret = scrapy.Field()
    one_month_ret = scrapy.Field()
    six_month_ret = scrapy.Field()
    dataset_name = scrapy.Field()

class LatestInsiderTrading(scrapy.Item):
    x = scrapy.Field()
    filling_date = scrapy.Field()
    trade_date = scrapy.Field()
    ticker = scrapy.Field()
    company = scrapy.Field()
    insider_name = scrapy.Field()
    insider_title = scrapy.Field()
    trade_type = scrapy.Field()
    share_price = scrapy.Field()
    shares_traded = scrapy.Field()
    shares_owned = scrapy.Field()
    own_chg = scrapy.Field()
    value_traded = scrapy.Field()
    one_day_ret = scrapy.Field()
    one_week_ret = scrapy.Field()
    one_month_ret = scrapy.Field()
    six_month_ret = scrapy.Field()
    dataset_name = scrapy.Field()

class Link(scrapy.Item):
    main_url = scrapy.Field()
    cluster_buy_url = scrapy.Field()
    top_officer_purchase_url = scrapy.Field()
