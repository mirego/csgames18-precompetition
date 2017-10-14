//
//  LoginControllerImpl.swift
//  Sherbook
//
//  Created by Sytten on 2017-10-14.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

class LoginControllerImpl : LoginController
{
    func login(username: String, password: String, completion: @escaping (_ user: User?, _ error: Error?) -> ()) {
        FeedService.login(username: username, password: password) { [weak self] (user, error) in
            completion(user, error)
        }
    }
    
    func register(username: String, password: String, completion: @escaping (_ error: Error?) -> ()) {
        FeedService.register(username: username, password: password) { [weak self] (error) in
            completion(error)
        }
    }
}
