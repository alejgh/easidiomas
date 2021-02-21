import React, { useState,useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { SearchBar } from 'react-native-elements';


import { View, Text,Image} from 'react-native';
function HomeScreen() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text>Home Screen</Text>
      </View>
    );
  }
  

  function LogoTitle() {

    const [search,setSearch] = useState('')

    const updateSearch = (search) => {
       setSearch(search)
    };

    return (
    <SearchBar
        placeholder="Type Here..."
        onChangeText={updateSearch}
        value={search}
      />
    );
  }

export default function SearchStackNavigator({navigation}){

    const Stack = createStackNavigator();



    return(
    <NavigationContainer independent  initialRouteName="Search">
        <Stack.Navigator
         screenOptions={{
            headerTintColor: 'white',
            headerStyle: { 
                backgroundColor: 'rgb(27, 42, 51)',
                borderBottomColor: '#435060',
                borderBottomWidth:1
            },
          }}>
            <Stack.Screen name="Search" component={HomeScreen} options={{ headerTitle: props => <LogoTitle {...props} /> }}/>
        </Stack.Navigator>
    </NavigationContainer>
    )
}