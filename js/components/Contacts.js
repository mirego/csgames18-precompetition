import React from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import ContactListItem from './ContactListItem';

export default class Contacts extends React.Component {
  constructor(props) {
    super(props);
    this.state  = {
      contacts: [
        {
          key: "1",
          firstName: "Elliot",
          lastName: "Anderson",
          profilePicUrl: "http://helpgrowchange.com/wp-content/uploads/2014/03/tb_profile_201303_round.png"
        }, {
          key: "2",
          firstName: "John",
          lastName: "Doe",
          profilePicUrl: "http://xzhpx4b1m3p3qm9eq23m79ga.wpengine.netdna-cdn.com/wp-content/uploads/2014/06/Dr-Halland-Round-Profile-Pic.png"
        },{
              key: "3",
              firstName: "Jane",
              lastName: "Doe",
              profilePicUrl: "http://thehumannetwork.ca/assets/greb-9c099d524e0fb1513549f6c809aea4f79d66c0fb36324d7cf616152b310f7e6e.png"
          },{
              key: "4",
              firstName: "Bob",
              lastName: "The Builder",
              profilePicUrl: "http://www.monkeypodmarketing.com/wp-content/uploads/2015/11/profile-round.png"
          },
          {
              key: "5",
              firstName: "Eve",
              lastName: "Dropping",
              profilePicUrl: "https://static1.squarespace.com/static/58ff29e9b8a79bf850836ee7/t/591f0a0f197aea5137518602/1496325124535/profile-round.png"
          }
      ]
    };
  }

  _renderContacts = ({ item }) => (
    <ContactListItem contact={item} />
  );

  render() {
    return (
      <View>
        <FlatList
          data={this.state.contacts}
          renderItem={this._renderContacts}
          style = {{paddingTop:5}}
        />
      </View>
    );
  }
}
