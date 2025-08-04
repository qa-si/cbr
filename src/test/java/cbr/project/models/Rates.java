package cbr.project.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rates {

    private float yuanRate;
    private float dollarRate;
    private float euroRate;


    public Rates(float yuanRate, float dollarRate, float euroRate) {
        this.yuanRate = yuanRate;
        this.dollarRate = dollarRate;
        this.euroRate = euroRate;
    }
}