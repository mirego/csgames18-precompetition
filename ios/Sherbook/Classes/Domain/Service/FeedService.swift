//
//  FeedService.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
// https://s3.amazonaws.com/shared.ws.mirego.com/competition/

import UIKit

class FeedService {
    private static var dev = "http://localhost:3000/"
    private static var prod = "http://130.211.216.25:3000/"
    private static let postsUrl = prod + "api/posts/"
    private static let userURL = prod + "api/users/"
    private static let createEP = "create"
    private static let loginUrl = prod + "api/login"
    

    static func getPosts(completion: @escaping (_ posts: [Post]?, _ error: Error?) -> ()) {
        HttpService.get(url: postsUrl) { (data, error) in
            guard let data = data else {
                completion(nil, error)
                return
            }
             do {
                let feed = try JSONDecoder().decode(Feed.self, from: data)
                completion(feed.posts, error)
             } catch {
                print("Error trying to convert data to JSON")
                completion(nil, NSError(domain: "FeedService", code: -1, userInfo: ["description" : "Invalid data"]))
             }
        }
    }
    
    static func composePost(text: String, author: String, completion: @escaping (_ posts: [Post]?, _ error: Error?) -> ()) {
        let data = "author="+author+"&message="+text
        HttpService.post(url: postsUrl + createEP, data: data) { (data, error) in
            guard let data = data else {
                completion(nil, error)
                return
            }
            do {
                let feed = try JSONDecoder().decode(Feed.self, from: data)
                completion(feed.posts, error)
            } catch {
                print("Error trying to convert data to JSON : \(NSString(data: data, encoding: String.Encoding.utf8.rawValue))")
                completion(nil, NSError(domain: "FeedService", code: -1, userInfo: ["description" : "Invalid data : \(data)"]))
            }
        }
    }
    
    static func login(username: String, password: String, completion: @escaping (_ user: User?, _ error: Error?) -> ()) {
        let data = "username="+username+"&password="+password
        HttpService.post(url: loginUrl, data: data) { (data, error) in
            guard let data = data else {
                completion(nil, error)
                return
            }
            // decode data
            do {
                let user = try JSONDecoder().decode(User.self, from: data)
                completion(user, error)
            } catch {
                print("Error trying to convert data to JSON : \(NSString(data: data, encoding: String.Encoding.utf8.rawValue))")
                completion(nil, NSError(domain: "FeedService", code: -1, userInfo: ["description" : "Invalid data : \(data)"]))
            }
        }
    }
    
    static func register(username: String, password: String, completion: @escaping (_ error: Error?) -> ()) {
        let data = "username="+username+"&password="+password
        HttpService.post(url: userURL, data: data) { (data, error) in
            guard let data = data else {
                completion(error)
                return
            }
            completion(nil)
        }
    }
}
