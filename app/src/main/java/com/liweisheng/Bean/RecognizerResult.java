package com.liweisheng.Bean;

import java.util.List;

/**
 * Created by 李维升 on 2017/12/21.
 * 这是语音听写后返回的结果Bean类
 */

public class RecognizerResult {
    private String sn;
    private String ls;
    private String bg;
    private String ed;

    private List<ws> ws;

    public List<ws> getWs() {
        return ws;
    }

    public void setWs(List<ws> ws) {
        this.ws = ws;
    }

    public static class ws{
        private String bg;
        private List<cw> cw;

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public List<cw> getCw(){
            return cw;
        }
        public void setCw(List<cw> cw){
            this.cw=cw;
        }

        public static class cw{
            private String sc;

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            private String w;

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }
        }
    }
}
