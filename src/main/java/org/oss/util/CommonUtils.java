package org.oss.util;

import org.oss.constant.CommonConstant;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 通用工具方法
 */
public class CommonUtils {
    /**
     * 将一串用`CommonConstant.SPLIT_ID_SIGN`分割的字符串拼接成`tableId` `not in` (a, b, c)的格式
     *
     * @param ids
     * @param tableId
     * @return
     */
    public static String madeSqlNotIn(String ids, String tableId) {
        return madeSqlNotIn(ids.split(CommonConstant.SPLIT_ID_SIGN), tableId);
    }

    /**
     * 将字符串数组拼接成`tableId` `not in` (a, b, c)的格式
     *
     * @param ids
     * @param tableId
     * @return
     */
    public static String madeSqlNotIn(String[] ids, String tableId) {
        return madeSqlNotIn(Arrays.asList(ids), tableId);
    }

    /**
     * 将字符串集合拼接成`tableId` `not in` (a, b, c)的格式
     *
     * @param ids
     * @param tableId
     * @return
     */
    public static String madeSqlNotIn(Collection<String> ids, String tableId) {
        return madeSqlForSign(ids, tableId, "not in");
    }

    /**
     * 将字符串集合拼接成 `tableId` `sign` (a, b, c)的格式
     *
     * @param ids
     * @param tableId
     * @param sign
     * @return
     */
    private static String madeSqlForSign(Collection<String> ids, String tableId, String sign) {
        if (CollectionUtils.isEmpty(ids)) {
            return " " + tableId + " " + sign + " ('') ";
        }

        // 分段临界值，超过num会分段
        int num = 1000;
        StringBuilder sb = new StringBuilder();
        if (ids.size() < num) {
            sb.append(" ").append(tableId).append(" ").append(sign).append(" (");
            ids.forEach(e -> sb.append("'").append(e.trim()).append("', "));
            sb.delete(sb.length() - 2, sb.length());
            sb.append(")");
        } else {
            // 每段大小
            int subLength = 900;
            sb.append("( ");
            int index = 0;
            for (String id : ids) {
                if (index % subLength == 0) {
                    sb.append(tableId).append(" ").append(sign).append(" (");
                }
                sb.append("'").append(id.trim()).append("'");
                if ((index + 1) % subLength == 0 || index == ids.size() - 1) {
                    sb.append(")");
                    if (index != ids.size() - 1) {
                        sb.append(" or ");
                    }
                } else {
                    sb.append(", ");
                }
                index++;
            }
            sb.append(" )");
        }

        return sb.toString();
    }

    /**
     * 将list拼接为sql的in条件。 list达到1000会分成多段
     *
     * @param list    list
     * @param tableId sql id
     * @return tableid in ('....','...','..')
     */
    public static String madeSqlIn(List<String> list, String tableId) {
        return madeSqlForSign(list, tableId, "in");
    }

    /**
     * 将ids拼接为sql的in条件。 list达到1000会分成多段
     *
     * @param ids
     * @param tableId
     * @return tableid in ('....','...','..')
     */
    public static String madeSqlIn(String ids, String tableId) {
        return madeSqlIn(Arrays.asList(ids.split(CommonConstant.SPLIT_ID_SIGN)), tableId);
    }

    /**
     * 将ids拼接为sql的in条件。 list达到1000会分成多段
     *
     * @param ids
     * @param tableId
     * @return tableid in ('....','...','..')
     */
    public static String madeSqlIn(String[] ids, String tableId) {
        return madeSqlIn(Arrays.asList(ids), tableId);
    }

    /**
     * 将ids拼接为sql的in条件。 达到1000会分成多段
     *
     * @param ids
     * @param tableId
     * @return tableid in ('....','...','..')
     */
    public static String madeSqlIn(Collection<String> ids, String tableId) {
        return madeSqlIn(new ArrayList<>(ids), tableId);
    }

    /**
     * 将ids拼接为sql的in条件。 list达到1000会分成多段,tableId默认为“”
     *
     * @param ids
     * @return
     */
    public static String madeSqlIn(String ids) {
        return madeSqlIn(ids, "");
    }

    /**
     * 时间转换成指定格式字符串
     *
     * @param d
     * @return
     */
    public static String changeDateToString(Date d, String dateFormat) {
        if (d == null) {
            return null;
        }
        return convertDateToLocalDateTime(d).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 将date转换成LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 把数据按照batchNum切分为等量大小
     * @param list
     * @param batchNum
     * @return
     */
    public static List<List> divideCollection(List list, int batchNum) {
        List<List> dividedData = new ArrayList<>();
        int startIndex = 0;
        int endIndex = batchNum;
        if (list == null) {
            return null;
        }
        if (list.size() <= batchNum) {
            dividedData.add(list);
        } else {
            while (startIndex < list.size()) {
                dividedData.add(list.subList(startIndex, endIndex));
                startIndex = endIndex;
                endIndex = (endIndex + batchNum) > list.size() ? list.size() : endIndex + batchNum;
            }
        }
        return dividedData;
    }
}
