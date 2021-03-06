/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package sync;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author flysLi
 * @ClassName ProductDataReq
 * @Decription TODO
 * @Date 2019/1/10 13:43
 * @Version 1.0
 */
public class ProductDataReq {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 5,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue());
        String[] names = {"黄瓜", "西红柿", "大白菜", "土豆", "芹菜", "青椒", "韭菜", "茄子", "大葱", "大蒜", "白萝卜", "生姜", "葱头", "菜苔", "菜花", "胡萝卜", "香菜", "冬瓜", "豆角", "菠菜", "蒜薹", "油菜", "洋白菜", "西葫芦", "生菜", "莲藕", "莴笋", "绿尖椒", "苦瓜", "南瓜", "条形辣椒干(二荆条)", "条形辣椒干(小米椒)", "圆形辣椒干(草莓椒)", "圆形辣椒干(子弹头)", "香菇", "小白菜", "平菇", "丝瓜", "西兰花", "山药", "茼蒿", "西洋芹", "蒜苗", "小葱", "芋头", "油麦菜", "红椒", "豇豆", "红萝卜", "空心菜", "荷兰豆", "金针菇", "韭黄", "茴香", "黄豆芽", "长茄子", "毛豆", "绿豆芽", "尖椒", "红尖椒", "茭白", "圆茄子", "瓠子", "佛手瓜", "木耳菜", "樱桃西红柿", "苋菜", "青冬瓜", "韭苔", "韭菜花", "鸡腿菇", "芥菜", "青笋", "水萝卜", "菜瓜", "香椿", "荸荠", "慈菇", "黑木耳", "玉米棒", "良薯", "茶树菇", "草菇", "光皮黄瓜", "冬笋", "辣椒干", "银耳", "杏鲍菇", "竹笋", "白灵菇", "豌豆", "凤尾菇", "豌豆尖", "莲蓬", "折耳根", "福鼎芋", "猴头菇", "毛木耳", "菜用仙人掌", "枸杞二级", "菜薹", "白蒜5.0公分", "红蒜5.0公分", "蘑菇", "白蒜6.0公分", "马蹄", "红蒜6.0公分", "心里美萝卜", "萝卜丝", "枸杞一级", "鸡蛋", "猪肉(白条猪)", "内三元母猪", "内三元生猪", "内三元仔猪", "牛肉", "外三元母猪", "外三元生猪", "外三元仔猪", "羊肉", "白条鸡", "鸭蛋", "活鸡", "乌鸡", "猪心", "基础母牛(500斤左右)", "麻花公鸡", "猪肝", "猪肚", "猪后腿肌肉", "三黄公鸡", "三黄鸡", "活鸭", "肥膘", "猪前腿肌肉", "猪肺", "猪大排肌肉", "猪大肠", "猪颈背肌肉", "猪肾", "老母鸡", "活鹅", "仔猪", "活兔", "毛猪", "柴鸡", "黄牛", "肥猪", "黄花母鸡", "蛇", "猪小肠", "山羊", "红皮鸡蛋", "猪皮", "白皮鸡蛋", "绵羊", "育肥牛", "水牛", "驴肉", "肉羊", "肉牛", "活驴", "肉马", "肉鸡苗(只)", "骡子", "耕骡", "耕马", "耕驴", "鸡苗(只)", "黄花公鸡(只)", "猪皮(张)", "役马", "驴板肠", "山羯羊", "山母羊", "驴皮(张)", "鸭苗(只)", "活草鱼", "白鲢活鱼", "活鲫鱼", "活鲤鱼", "黄鳝", "武昌鱼", "带鱼", "黑鱼", "鲈鱼", "泥鳅", "小黄鱼", "章鱼", "沼虾", "大带鱼", "人工甲鱼", "养殖鲶鱼", "大黄鱼", "马鲅鱼", "小带鱼", "小黄花鱼", "牛蛙", "基围虾", "草鱼", "鲫鱼", "鲢鱼", "罗非鱼", "鲤鱼", "大平鱼", "活鳜鱼", "小平鱼", "白鳝鱼", "对虾", "草虾", "梭子蟹", "虹鳟鱼", "扇贝", "蛏子", "蛤蜊", "澳洲龙虾", "野生甲鱼", "海参", "石斑鱼", "青蟹", "野生鲶鱼", "海鳗", "花鲢活鱼", "江蟹", "海蛎", "鲍鱼", "乌龟", "田鸡", "加吉鱼", "鲅鱼", "肉蟹", "紫菜", "美洲龙虾", "鲨鱼", "土虾", "淡菜(鲜)", "野生大黄鱼", "鳗鱼干", "深水网箱大黄鱼", "真鲷", "鮸鱼", "大黄花鱼", "鳊鱼", "面粉", "绿豆", "色拉油", "大豆", "一级豆油(散装)", "红小豆", "粳米(普通)", "菜油(散装)", "标准粉", "S33(油葵)", "高粱", "黑中片籽瓜子", "西葫芦白瓜籽(炒货)", "玉米", "芸豆", "大米", "花生油", "香油", "特一粉", "花生仁", "籼米(晚籼米)", "花生", "棕榈油", "豆油", "菜油", "特二粉", "粳米", "糯米", "籼米", "葵花籽", "黑大片葵花籽", "晚籼稻", "早籼稻", "芝麻(白芝麻)", "白芝麻", "香蕉", "富士苹果", "西瓜", "哈密瓜", "菠萝", "伽师瓜", "巨峰葡萄", "蜜桔", "鸭梨", "猕猴桃", "白桃", "红马奶葡萄干", "金丝枣", "菱角", "龙眼(储良)", "龙眼(石硖)", "绿马奶葡萄干", "芒果(红象牙9号)", "芒果(农院3号)", "芒果(台农一号)", "柚", "脐橙", "香梨", "红提子", "芒果", "龙眼(桂圆)", "桃子", "草莓", "甘蔗", "椪柑", "广柑", "芦柑", "金龙芒", "芒果(农院5号)", "雪梨", "红枣", "香瓜", "山竹", "酥梨", "核桃", "柿子", "蛇果", "油桃", "柠檬", "黄河蜜", "石榴", "玫瑰香葡萄", "伊丽沙白瓜", "甜橙", "国光苹果", "青提子", "马奶葡萄", "荔枝", "荔枝(白腊)", "荔枝(桂味)", "荔枝(黑叶)", "荔枝(妃子笑)", "山楂", "板栗", "樱桃", "红毛丹", "杏子", "青苹果", "黄元帅苹果", "布朗", "李子", "人参果", "杨桃", "苹果梨", "大枣", "丰水梨", "乔纳金苹果", "红星苹果", "黄香蕉苹果", "椰子", "蟠桃", "冬枣", "葡萄干(通货)", "麒麟西瓜", "白梨", "杨梅", "秦冠苹果", "枇杷", "特小凤西瓜", "黑美人西瓜", "网纹瓜", "常山胡柚", "沙田柚", "嘎拉苹果", "红香蕉苹果", "甜瓜", "早春红玉瓜", "龙眼葡萄", "雪莲果", "京欣西瓜", "葡萄干(特级)", "象牙芒", "砂糖橘", "华兰氏瓜", "黄金瓜", "年桔", "芒果(金煌芒)", "白糖罐甜瓜", "红肖梨", "芒果(象芽22号)", "石榴(大籽石榴)", "芒果(红象芽9号)", "干枣", "鲜枣", "葡萄干(二级)", "葡萄干(一级)", "海棠果", "花盖梨", "菱枣", "木瓜", "葡萄", "青枣", "香水梨"};
        for (String name : names) {
            executor.execute(new ReqData(name));
        }
        System.out.println("任务分配完毕");
    }
}
