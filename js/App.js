import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import Swiper from 'react-native-swiper';

import Header from './containers/Header';
import Content from './containers/Content';
import NavMenu from './components/NavMenu';
import Feed from './components/Feed';
import Messages from './components/Messages';
import Contacts from './components/Contacts';
import Settings from './components/Settings';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      "selectedTabIndex": 0
    };
    this._swiper = null;
  }

  setSwiper(_swiper) {
    this._swiper = _swiper;
  }

  handleSwiperIndexChange(index) {
    // TODO: handle index change
    console.log(this._swiper != null);
  }

  render() {
    return (
      <View style={ styles.container }>
        <Header>
          <NavMenu />
        </Header>
        <Button
          onPress={() => { this._swiper.scrollBy(2) }}
          title="Press Me"
        />
        <Content>
          <Swiper ref={(_swiper) => { this._swiper = _swiper; }} onIndexChanged={this.handleSwiperIndexChange} loop={true} showsPagination={false}>
            <Feed />
            <Messages />
            <Contacts />
            <Settings />
          </Swiper>
        </Content>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1
  },
});
