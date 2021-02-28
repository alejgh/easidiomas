import React, { useState,useEffect, useContext } from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Post from './items/Post';
import { FloatingAction } from "react-native-floating-action";
import Ionicons from 'react-native-vector-icons/Ionicons'
import { AppContext } from '../App';

export default function Home(props) {
  
  const{parentNavigation,navigation,filters} = props;
  const context = useContext(AppContext);
  const {REQUEST_URI} = context.CONFIG;

  const [posts, setPosts] = useState([]);
  const [links,setLinks] = useState([]);

  const loadPosts = async function(url){
    let response = await (await fetch(REQUEST_URI+url+filters,{
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'token':context.token
        }})).json();
    let data = response.data;
    let newPosts = [];
    for(let post in data){
      let user = await getUser('/users/'+data[post].authorId);
      newPosts.push({
        id:data[post].id,
        user:user,
        content:data[post].content,
        numLikes:data[post].likes
      })
    }

    //setPosts([...posts,newPosts]) -> I´m not sure why this is not working...
    setPosts(posts.concat(newPosts))
    setLinks(response.links)
  }

  const loadNext = function(){
    if(links?.next){
      loadPosts(links.next.replace('api/','/'))
    }
      
  }


  const getUser = async function(url){
    return (await fetch(REQUEST_URI+url,{
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token':context.token
      }})).json();
  }

  useEffect(()=>{
    loadPosts('/posts');
  },[])

  const actions = [
    {
      text: "Accessibility",
      name: "bt_accessibility",
      icon: <Ionicons name={'add-outline'}  size={25} color={'#fff'} />,
      position: 1
    },]

  return (
    <View style={styles.container}>

      <FlatList 
        numColumns={1}
        onEndReachedThreshold={0.01}
        onEndReached={info => {
          loadNext();
        }}
        keyExtractor={(item) => item.id} 
        data={posts} 
        renderItem={({ item }) => ( 
            <Post key={item.id} postId={item.id} parentNavigation={parentNavigation} user={item.user} content={item.content} numLikes={item.numLikes}/>
        )}
      />

      <FloatingAction
          color={'#1DA1F2'}
          animated={false}
          actions={actions}
          overrideWithAction
          onPressItem={name => {
            navigation.navigate("New Post");
          }}
        />

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    justifyContent: 'center',
  },
  item: {
    flex: 1,
    marginHorizontal: 10,
    marginTop: 24,
    padding: 30,
    backgroundColor: '#1b2836',
    fontSize: 24,
  },
});
