/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.example.sipstool;

import java.util.ArrayList;

import com.example.json.ParkingDetailObject;
import com.example.sipsproject2.R;
import com.example.sipsproject2.R.drawable;
import com.google.android.gms.maps.model.LatLng;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class Constants {

	public static final String[] IMAGES = new String[] {
			// Heavy images
		    "drawable://" + R.drawable.abc,
		    "drawable://" + R.drawable.pro2,
		    "drawable://" + R.drawable.pro3,
		    "drawable://" + R.drawable.pro4,
		    "drawable://" + R.drawable.pro5,
		    "drawable://" + R.drawable.pro1,
	};

	private Constants() {
	}
	
	public static ArrayList<ParkingDetailObject> getParkingObject(){
	ArrayList<ParkingDetailObject> Parking = new ArrayList<ParkingDetailObject>();
	Parking.add(new ParkingDetailObject(1, mall1[0], mall1[1], mall1[2], mall1[3], mall1[4], mall1[5], mall1[6], 1,  new LatLng(13.816634, 100.561029),3000));
	Parking.add(new ParkingDetailObject(2, mall2[0], mall2[1], mall2[2], mall2[3], mall2[4], mall2[5], mall2[6], 1,  new LatLng(13.746384, 100.539447),2500));
	Parking.add(new ParkingDetailObject(3, mall3[0], mall3[1], mall3[2], mall3[3], mall3[4], mall3[5], mall3[6], 2,  new LatLng(13.8555	, 100.541888),2700));
	Parking.add(new ParkingDetailObject(4, mall4[0], mall4[1], mall4[2], mall4[3], mall4[4], mall4[5], mall4[6], 2,  new LatLng(13.714242, 100.407874),700));
	Parking.add(new ParkingDetailObject(5, mall5[0], mall5[1], mall5[2], mall5[3], mall5[4], mall5[5], mall5[6], 1,  new LatLng(13.757531, 100.565778),3500));
	Parking.add(new ParkingDetailObject(5, mall6[0], mall6[1], mall6[2], mall6[3], mall6[4], mall6[5], mall6[6], 2,  new LatLng(13.764688, 100.643325),3500));
	//Parking.add();
	return Parking;}
	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}
	
	private static String[] mall1 ={"Central Ladprao","Shopping Dining","valet,reservation","1693 Phahonyothin Road,Bangkok, Thailand"
		,"tel 0-2793-7000","Alumnus,Lolita,EP,Morgan,Juicy couture,Evita Peroni,Adidas,Nike,Armani,Lacoste",
		"FUJI,Ootoya,Chabuton,Coco Ichibanya,AKA,The Pizza company, Red Manga,Zen,Starbucks,Oishi Ramen,Mister Donut"
		};
	private static String[] mall2 ={"Central World","Shopping Dining Cinema","valet,reservation","999/9 Rama1 Road,Bangkok, Thailand"
		,"tel 0-2793-7000","Bossini,Zara,XOXO,Jaspal,Diva,Arsenal,Juicy couture,Evita Peroni,Adidas,Nike,Armani,Lacoste",
		",Coco Ichibanya,Jackie,Bonito,Nara,Nobu Shabu, Red Manga,Zen,Starbucks,Oishi Ramen,Mister Donut"
		};
	private static String[] mall3 ={"The Mall Ngamwongwan","Dining Cinema","reservation","30/39-50 Ngamwongwan Road,Nonthaburi,Thailand"
		,"tel 0-2792-5671","Jaspal,DivaAdidas,Nike,Armani,,Levis,Dapper",
		",Oschi,Manu,KFC,MK Resturant,Jackie,Bonito,Nara,Nobu Shabu, Red Manga,Zen,Starbucks,Oishi Ramen,Mister Donut"
		};
	private static String[] mall4 ={"The Mall Bangkae","Dining Cinema","reservation","275 Phetkasem Road,Bangkok,Thailand"
		,"tel 0-2792-5671","Jaspal,DivaAdidas,Nike,Armani,,Levis,Dapper",
		",Oschi,Manu,KFC,MK Resturant,Jackie,Bonito,Nara,Nobu Shabu, Red Manga,Zen,Starbucks,Oishi Ramen,Mister Donut"
		};
	private static String[] mall5={"Central Grand Rama9","Shopping Dining Cinema","valet","9/8-9 Ratchada Road,Bangkok, Thailand"
		,"tel 0-2793-7000","Bossini,Zara,XOXO,Jaspal,Juicy couture,Evita Peroni,Adidas,Nike,Armani,Lacoste",
		",Coco Ichibanya,Jackie,Bonito,Nara,Nobu Shabu, Red Manga,Zen,Starbucks,Oishi Ramen,Mister Donut"};
	
	private static String[] mall6 ={"The Mall Bangkapi","Dining","reservation","275 Phetkasem Road,Bangkok,Thailand"
		,"tel 0-2792-5671","Jaspal,DivaAdidas,Nike,Armani,,Levis,Dapper",
		",Oschi,Manu,KFC,MK Resturant,Jackie,Bonito,Nara,Nobu Shabu, Red Manga,Zen,Starbucks,Oishi Ramen,Mister Donut"
		};
	public static String[] mall_name={"Central Ladprao","Central World","The Mall Ngamwongwan","The Mall Bangkae","Central Grand Rama9","The Mall Bangkapi"};
	public static class Extra {
		public static final String IMAGES = "com.nostra13.example.universalimageloader.IMAGES";
		public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
	}
}
