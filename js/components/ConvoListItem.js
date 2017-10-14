import React from 'react';
import { StyleSheet, Text, View, Image, Dimensions } from 'react-native';

export default class ConvoListItem extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      author: props.author,
      messages: props.messages
    };
  }

  render() {
    return (
      <View style={styles.messageContainer}>
        <Text style={styles.title}>{this.state.author}: </Text>
        <Text style={styles.lastMessage}>{this.state.messages[0]}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  messageContainer: {
    flexDirection: 'row',
    marginBottom: 8,
    padding: 10,
    backgroundColor: 'white',
  },
  title: {
    paddingLeft: 10,
    marginBottom: 5,
    fontWeight: 'bold',
    color: 'black'
  },
  lastMessage: {
    alignSelf: 'flex-end',
    paddingLeft: 10,
    marginBottom: 5,
    color: 'grey'
  }
});