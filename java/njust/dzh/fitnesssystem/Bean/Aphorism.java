package njust.dzh.fitnesssystem.Bean;

import java.util.List;

public class Aphorism {
//    {
//            "code": 200,
//            "msg": "success",
//            "newslist": [
//        {
//                 "saying": "",
//                "transl": "",
//                "source": ""
//        }
//  ]
//    }
    private int code;
    private String msg;
    private List<NewslistDTO> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistDTO> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistDTO> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistDTO {
        private String saying;
        private String transl;
        private String source;

        public String getSaying() {
            return saying;
        }

        public void setSaying(String saying) {
            this.saying = saying;
        }

        public String getTransl() {
            return transl;
        }

        public void setTransl(String transl) {
            this.transl = transl;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}
