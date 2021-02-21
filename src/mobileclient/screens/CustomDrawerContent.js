import * as React from 'react';
import { View } from 'react-native';
import {
  StyleSheet,
  Text,
  Image,
  TouchableOpacity,
  ScrollView
} from 'react-native'

import FontAwesome from 'react-native-vector-icons/FontAwesome'
import Ionicons from 'react-native-vector-icons/Ionicons'

export function CustomDrawerContent({ navigation }) {
  return (
    <View style={styles.container}>
        <View style={styles.top}>
          <Image
            onPress={() => this.props.navigation.navigate('Profile')}
            source={require('../assets/avatar.png')}
            style={styles.photo}/>
          <Text style={styles.userName}>Pablo Menéndez 🐼 </Text>
          <Text style={styles.userHandle}>@mistermboy </Text>

          <View>
            <Text style={styles.followingCount}>970
              <Text
                style={styles.followingText}>Following</Text>
            </Text>
            <Text style={styles.followersCount}>1,325
              <Text
                style={styles.followersText}>Followers</Text>
            </Text>
          </View>

        </View>

        <ScrollView>
          <TouchableOpacity
            onPress={() => navigation.navigate('Profile')}
            style={[ styles.list, styles.firstList]}>
            <View>
              <FontAwesome
                style={styles.icon}
                name='user-o'
                size={20}
                color="rgb(136, 153, 166)"/>
              <Text style={styles.text}> Profile </Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => navigation.navigate('Search')} style={styles.list}>
            <View>
              <Ionicons
                style={styles.icon}
                name='search-outline'
                size={20}
                color="rgb(136, 153, 166)"/>
              <Text style={styles.text}> Search </Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => navigation.navigate('Posts')} style={styles.list}>
            <View>
              <Ionicons
                style={styles.icon}
                name='albums-outline'
                size={20}
                color="rgb(136, 153, 166)"/>
              <Text style={styles.text}> Posts </Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity
            style={[
            styles.list, {
              borderBottomWidth: 0.3,
              borderBottomColor: 'black'
            }
          ]}>
            <View>
              <Ionicons
                style={styles.icon}
                name='chatbox-outline'
                size={20}
                color="rgb(136, 153, 166)"/>
              <Text onPress={() => navigation.navigate('Messages')} style={styles.text}> Messages </Text>
            </View>
          </TouchableOpacity>
         
          <TouchableOpacity style={styles.list}>
            <View>
              <Text
                onPress={() => navigation.navigate('Profile')}
                style={[
                styles.text, {
                  left: 20
                }
              ]}>
                Settings and privacy
              </Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity style={styles.list}>
            <View>
              <Text
                onPress={() => navigation.navigate('Profile')}
                style={[
                styles.text, {
                  left: 20
                }
              ]}>
                Help Centre
              </Text>
            </View>
          </TouchableOpacity>
        </ScrollView>
      </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'rgb(27, 42, 51)',
    paddingTop: 10
  },
  list: {
    padding: 10,
    height: 60,
    borderColor: 'red',
    borderWidth: 0
  },
  text: {
    position: "absolute",
    left: "24%",
    top: 10,
    color: "white",
    fontSize: 16
  },
  top:{
    paddingBottom: 40,
    paddingLeft: 30,
    marginBottom:10
  },
  photo: {
    width: 50,
    height: 50,
    borderRadius: 30,
    marginTop: 20
  },
  userName:{
    marginTop: 15,
    color: "white",
    fontWeight: "bold"
  },
  userHandle:{
    marginTop: 15,
    color: "rgb(136, 153, 166)",
    fontWeight: "300"
  },
  followingCount:{
    color: "white",
    position: 'absolute',
    left: 0,
    top: 10,
    fontWeight: "bold"
  },
  followingText:{
    color: "rgb(136, 153, 166)",
    fontWeight: "300"
  },
  followersCount:{
    color: "white",
    position: 'absolute',
    right: 30,
    top: 10,
    fontWeight: "bold"
  },
  followersText:{
    color: "rgb(136, 153, 166)",
    fontWeight: "300"
  },
  firstList:{
    marginTop: 0,
    borderTopWidth: 0.3,
    borderTopColor: 'black',
    height: 60,
    borderTopWidth: 0.3,
    borderTopColor: 'black'
  },
  icon:{
    position: "absolute",
    left: 20,
    top: 10
  }
})