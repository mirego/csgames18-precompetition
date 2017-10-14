import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import NavList from '../components/NavList';

export default class NavMenu extends React.Component {
  static defaultProps = {
      pages: ['Home', 'Friends', 'Messages', 'Settings'],
      current: 'Home',
      onPageSelected: (title) => {}
  }

  render() {
    return (
      <View style={{flex: 1}}>
        <View style={styles.currentPage}>
            <View style={{flexDirection: 'row'}}>
              <Text style={[styles.currentPageButton, styles.text]}>{this.props.current}</Text>
              <View style={styles.currentPageButton}></View>
            </View>
        </View>
        <View style={styles.navBar}>
          <NavList
            pages={this.props.pages}
            current={this.props.current}
            onPageSelected={(title) => {this.props.onPageSelected(title)}} />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  currentPage: {
    flex: 3,
    justifyContent: 'center',
    paddingLeft: 25
  },
  currentPageButton: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  navBar: {
    flex: 2,
    backgroundColor: 'white',
  },
  text: {
    color: '#FFFFFF',
    fontSize: 24
  }
});
