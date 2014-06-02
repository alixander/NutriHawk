import sys
import urllib
import requests
import re
from bs4 import BeautifulSoup

def main():

	def get_amount(mapping, nutrition):
		found_index = nutrition.find(mapping)
		start_index = nutrition[found_index:].find('"')+found_index+1
		end_index = nutrition[start_index+1:].find('"')+start_index
		dv_string_value = nutrition[start_index:end_index+1]
		if "~" in dv_string_value:
			daily_value = 0.0
		else:
			daily_value = float(dv_string_value)
		found_index = nutrition.find(" " + mapping[3:])
		start_index = nutrition[found_index:].find('"')+found_index+1
		end_index = nutrition[start_index+1:].find('"')+start_index
		base_value = nutrition[start_index:end_index+1]
		if daily_value != 0.0:
			return 100 * (float(base_value)*33.0/100)/daily_value
		else:
			return 0.0

	f = open('java_output', 'w')
	sources = open('sources', 'r')

	map1 = {}
	map1['DV_NUTRIENT_97'] = 'vitamin_a'
	map1['DV_NUTRIENT_107'] = 'vitamin_b1'
	map1['DV_NUTRIENT_108'] = 'vitamin_b2'
	map1['DV_NUTRIENT_109'] = 'vitamin_b3'
	map1['DV_NUTRIENT_116'] = 'vitamin_b5'
	map1['DV_NUTRIENT_110'] = 'vitamin_b6'
	map1['DV_NUTRIENT_111'] = 'vitamin_b9'
	map1['DV_NUTRIENT_115'] = 'vitamin_b12'
	map1['DV_NUTRIENT_100'] = 'vitamin_c'
	map1['DV_NUTRIENT_101'] = 'vitamin_d'
	map1['DV_NUTRIENT_102'] = 'vitamin_e'
	map1['DV_NUTRIENT_103'] = 'vitamin_k'
	map1['DV_NUTRIENT_117'] = 'calcium'
	map1['DV_NUTRIENT_118'] = 'iron'
	map1['DV_NUTRIENT_119'] = 'magnesium'
	map1['DV_NUTRIENT_120'] = 'phosphorus'
	map1['DV_NUTRIENT_121'] = 'potassium'
	map1['DV_NUTRIENT_122'] = 'sodium'
	map1['DV_NUTRIENT_123'] = 'zinc'
	map1['DV_NUTRIENT_124'] = 'copper'
	map1['DV_NUTRIENT_125'] = 'manganese'

	for line in sources:
		name = line[:line.find(".")]
		url = line[line.find(".")+1:]
		contents = requests.get(url).text
		nutrition = re.findall(r'foodNutrients = ({.*?})', contents, re.DOTALL)
		map2 = {}
		for mapping in map1.keys():
			amount = str(int(round(get_amount(mapping, str(nutrition)))))
			map2[map1[mapping]] = amount
		vitamin_middle_chunk = map2["vitamin_a"] + ", " + map2["vitamin_b1"] + ", " + map2["vitamin_b2"] + ", " + map2["vitamin_b3"] + ", " +map2["vitamin_b5"] + ", " + map2["vitamin_b6"] + ", " + map2["vitamin_b9"] + ", " +map2["vitamin_b12"] + ", " +map2["vitamin_c"] + ", " +map2["vitamin_d"] + ", " +map2["vitamin_e"] + ", " + map2["vitamin_k"]
		vitamin_string_output = 'sourcesOfVitamins.put('+ name.upper() + ', new VitaminSet('+ vitamin_middle_chunk + '));'
		mineral_middle_chunk = map2["calcium"] + ", " + map2["iron"] + ", " + map2["magnesium"] + ", " +map2["phosphorus"] + ", " + map2["potassium"] + ", " + map2["sodium"] + ", " + map2["zinc"] + ", " + map2["copper"] + ", " +map2["manganese"]
		mineral_string_output = 'sourcesOfMinerals.put('+ name.upper() + ', new MineralSet('+ mineral_middle_chunk + '));'
		f.write(vitamin_string_output + "\n" + mineral_string_output + "\n")
	

if __name__ == '__main__':
	main()