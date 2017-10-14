import React from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import ConvoListItem from './ConvoListItem';

export default class Messages extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      groupedMessages: [
        {
          key: "ALSKJDASD79",
          author: "Johnson",
          messages: [
            {
              senderName: "Johnson",
              message: "Allo"
            },
            {
              senderName: "You",
              message: "Yo"
            }
          ]
        },
        {
          key: "ALSKJDASD78",
          author: "Arnold",
          messages: [
            {
              senderName: "Arnold",
              message: "Sup"
            }, {
              senderName: "You",
              message: "How you been?"
            }
          ]
        }
      ]
    };
  }

  _renderMessages = ({ item }) => {
    return (
      <ConvoListItem author={item.author} messages={item.messages} />
    );
  };

  render() {
    return (
      <View>
        <FlatList
          data={this.state.groupedMessages}
          renderItem={this._renderMessages}
        />
      </View>
    );
  }
}
