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

    public RecognizerResult() {
    }

    private List<ws> wsList;

    public List<ws> getWsList() {
        return wsList;
    }

    public void setWsList(List<ws> wsList) {
        this.wsList = wsList;
    }

    public class ws{
        private String bg;
        private cw cw;

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public cw getCw(){
            return cw;
        }
        public void setCw(cw cw){
            this.cw=cw;
        }

        public class cw{
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
