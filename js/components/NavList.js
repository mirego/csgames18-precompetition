import React from 'react';
import { Button, StyleSheet, Text, View, TouchableHighlight } from 'react-native';

export default class NavList extends React.Component {
  static defaultProps = {
      pages: ['Home', 'Friends', 'Messages', 'Settings'],
      current: 'Home',
      onPageSelected: (title) => {}
  }

  renderPageButton(title) {
    let style = styles.pageButton;
    if (this.props.current == title) {
      style = styles.pageButtonSelected;
    }

    return(
      <TouchableHighlight
        underlayColor="white"
        onPress={() => this.props.onPageSelected(title)}
        key={title}
        style={style}
      >
        <Text>{title}</Text>
      </TouchableHighlight>
    );
  }

  render() {
    return (
      <View style={{flex: 1, flexDirection: 'row'}}>
        {this.props.pages.map((title) => this.renderPageButton(title))}
      </View>
    );
  }
}

styles = StyleSheet.create({
  pageButton: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    borderBottomColor: 'white',
    borderBottomWidth: 2
  },
  pageButtonSelected: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    borderBottomColor: '#00743b',
    borderBottomWidth: 2
  }
});
