import React, { useState } from 'react';
import { StyleSheet, Text, View} from 'react-native';
/*import MultiSlider from '@ptomasroos/react-native-multi-slider';

 <MultiSlider
            values={[multiSliderValue[0], multiSliderValue[1]]}
            onValuesChange={multiSliderValuesChange}
            sliderLength={250}
            min={15}
            max={80}
            allowOverlap
            enableLabel
            customLabel={CustomLabel}
            
        />       

*/
import CustomLabel from './CustomLabel';
import CustomPickerPodal from './CustomPickerModal';

export default function FiltersScreen() {

    const [multiSliderValue, setMultiSliderValue] = useState([20, 35]);

    const multiSliderValuesChange = values => setMultiSliderValue(values);

  return (
    <View style={styles.container}>
       
       <CustomPickerPodal labelTag={"Native"}/>
       <CustomPickerPodal labelTag={"Learning"}/>
    
      <View style={styles.ageContainer}>
        <Text style={styles.ageLabel}>Age Range</Text>
        
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    borderColor:'#fff',
    borderTopWidth:1,
  },
  ageContainer:{
    flex: 0.2,
    backgroundColor: '#1b2836',
    justifyContent:'center',
    alignItems: 'center',
    flexDirection: 'column',
    borderColor:'#fff',
    borderBottomWidth:1,
  },
  ageLabel:{
    color:'white',
    fontSize:18,
    marginTop:10
  }
});
