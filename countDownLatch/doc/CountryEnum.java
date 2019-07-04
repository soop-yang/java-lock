package com.tedu.eurekaserver.enume;

public enum CountryEnum {
    ONE(1,"齐"), TWO(2,"魏"), THREE(3,"楚"), FOUR(4,"韩"), FIVE(5,"燕"), SIX(6,"赵");
    private Integer code;
    private String country;
    CountryEnum(Integer code, String country) {
        this.code = code;
        this.country = country;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public static CountryEnum setMsg(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum countryEnum:values) {
            if(index==countryEnum.getCode()){
                return countryEnum;
            }
        }
        return null;
    }



}
