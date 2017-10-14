import React from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Post from './Post';

export default class Feed extends React.Component {
  constructor(props){
    super(props);
    this.state = {
        "feed": "fdsa",
        "lastUpdate": "2017-10-13T21:42:20-0400",
        "posts": [{
            "key": "zbLHl5QzTFQo",
            "date": "2017-10-13T20:13:23-0400",
            "author": "Génie 5@8",
            "message": "Ahh l'automne. Les feu!!illes commencent à tomber, les couleurs changent, les bonbons sont pas trop chers au Walmart, le bon temps quoi! Cette semaine, on te réserve même une activité que tu ne voudra pas manquer.",
            "attachment": {
                "type": "image",
                "url": "https://s3.amazonaws.com/shared.ws.mirego.com/competition/images/19225167_1944400129183097_741905922742649424_n.jpg"
            }
        }, {
            "key": "LzKdigX3beH6",
            "date": "2017-10-13T17:56:30-0400",
            "author": "Faculté des sciences",
            "message": "Félicitations à toute l'équipe!",
            "attachment": {
                "type": "image",
                "url": "https://s3.amazonaws.com/shared.ws.mirego.com/competition/images/22289952_1468812669863074_1564894273065314200_o.jpg"
            }
        }, {
            "key": "90FcLaXYizIw",
            "date": "2017-10-13T17:25:35-0400",
            "author": "Génie 5@8",
            "message": "Félicitations à nos deux gagnants, Thomas Grégoire et Scotch Dupont qui passeront la partie du Vert et Or de samedi prochain dans une loge VIP grâce à Vkeyéotron et Génie 5à8 🏆🏈🎉",
            "attachment": null
        }, {
            "key": "KmD5Zi0gfiYt",
            "date": "2017-10-13T00:16:42-0400",
            "author": "Faculté de génie",
            "message": "Une équipe de chercheurs canadiens travaille sur le premier fauteuil roulant multifonctionnel autonome développé à très faible coût. Le Pr François Michaud et un de ses étudiants au doctorat, Mathieu Labbé, font partie de l'équipe. #rechercheUdeS #selfdrivingwheelchair",
            "attachment": null
        }, {
            "key": "2UMSqhZr9qD5",
            "date": "2017-10-12T23:46:13-0400",
            "author": "Génie 5@8",
            "message": "Équipe Juin 2015 en feu! 🔥",
            "attachment": {
                "type": "image",
                "url": "https://s3.amazonaws.com/shared.ws.mirego.com/competition/images/10285280_1615907055365741_2342967662454531701_o.jpg"
            }
        }, {
            "key": "9p3y47th9u9Er",
            "date": "2017-10-12T20:01:04-0400",
            "author": "Génie 5@8",
            "message": "Cette semaine au 5@8, prenez-vous en photo au photobooth de l'Oktoberfest Sherbrooke et courez la chance de gagner 1 REMPLISSAGE GRATOS DE 1L à l'OKTO 🎉🤸🏼💚💛",
            "attachment": {
                "type": "image",
                "url": "https://s3.amazonaws.com/shared.ws.mirego.com/competition/images/22195343_2000021746954268_777494157718426353_n.jpg"
            }
        }, {
            "key": "9p3y47th9u58Er",
            "date": "2017-10-12T20:01:04-0400",
            "author": "Génie 5@8",
            "message": "Cette semaine au 5@8, prenez-vous en photo au photobooth de l'Oktoberfest Sherbrooke et courez la chance de gagner 1 REMPLISSAGE GRATOS DE 1L à l'OKTO 🎉🤸🏼💚💛",
            "attachment": {
                "type": "image",
                "url": "https://s3.amazonaws.com/shared.ws.mirego.com/competition/images/22195343_2000021746954268_777494157718426353_n.jpg"
            }
        }, {
            "key": "9p3y47th9uE5r",
            "date": "2017-10-12T20:01:04-0400",
            "author": "Génie 5@8",
            "message": "Cette semaine au 5@8, prenez-vous en photo au photobooth de l'Oktoberfest Sherbrooke et courez la chance de gagner 1 REMPLISSAGE GRATOS DE 1L à l'OKTO 🎉🤸🏼💚💛",
            "attachment": {
                "type": "image",
                "url": "https://s3.amazonaws.com/shared.ws.mirego.com/competition/images/22195343_2000021746954268_777494157718426353_n.jpg"
            }
        }]
    };
  }
  _renderPosts = ({item}) => (
      <Post author={item.author} message={item.message} attachment={item.attachment}/>
  );
  render() {
    return (
        <View>
          <FlatList
              data = {this.state.posts}
              renderItem={this._renderPosts}
          />
        </View>
    );
  }
}
