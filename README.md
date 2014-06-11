NutriHawk
===========

The hawk that keeps an eye out for your nutrient deficiencies. 

![CompositeScreenshot](https://raw.github.com/alixander/NutriHawk/master/design/composite.png)

### Description

Nutrition tracking app that keeps tabs on vitamins and minerals. Input food and your vitamin and mineral uptake will be updated, with nice charts to review what foods contributed to what nutrients and how much of the nutrient you had in the past week. Also has a feature that advises what nutrient you're most in need of based on when's the last time you had it. Text on specific nutrient pages also tells you deficiency symptoms, rich sources, and benefits. 

### Specifics

Since it's designed to be used as an estimate and not a strict counter like calorie and carb/protein/fat trackers are, your daily value will be considered to be met after 50% suggested daily value has been reached. Line chart plots past 7 days (of course, data is kept longer), and the "need more of" is also based on the nutrition with the least intake in the past week. All measurements are based on roughly 1 cup's worth. Doesn't include certain nutrients in which deficiency among humans is rare (because why would you need to track it then).

### Why

Most apps are designed around weight loss or muscle gain and so keep track of your calorie intake or the amount of proteins you've consumed. This one focuses on health, because deficiency in a vitamin or mineral is a common occurance, especially among people with less varied diets. 

### Misc

Nutritional content information scraped off [http://nutritiondata.self.com/](http://nutritiondata.self.com/). 

SDK's 14+. 

Uses Telerik Android charting features.


### Licence

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.