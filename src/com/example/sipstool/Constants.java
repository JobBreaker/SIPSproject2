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

import com.example.sipsproject2.R;
import com.example.sipsproject2.R.drawable;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class Constants {

	public static final String[] IMAGES = new String[] {
			// Heavy images
		    "drawable://" + R.drawable.abc,
			"http://yogaaum.com/wp-content/uploads/2013/02/poster-promotion-R1.jpg",
			"http://www.promotiontoyou.com/wp-content/uploads/2013/08/Promotion-Rainy-Day-Deal-Dinner-Buffet-Save-50-@-Artium-Landmark-Hotel-full.jpg",
			"http://www.promotiontoyou.com/wp-content/uploads/2013/01/promotion-adidas-sale-up-to-30-off-central-rama-9-jan-2013-full.jpg",
			"http://www.compomax.co.th/compomax/wp-content/uploads/promotion-02.jpg",
			"http://www.sfcinemacity.com/site_images/image/OMGPromotion.jpg",
			"http://a.hiphotos.baidu.com/album/w%3D2048/sign=004abf07503d26972ed30f5d61c3b0fb/023b5bb5c9ea15ce967562c4b7003af33b87b260.jpg"
	};

	private Constants() {
	}

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}
	
	public static class Extra {
		public static final String IMAGES = "com.nostra13.example.universalimageloader.IMAGES";
		public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
	}
}
