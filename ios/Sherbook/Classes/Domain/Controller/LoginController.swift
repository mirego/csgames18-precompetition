//
//  LoginController.swift
//  Sherbook
//
//  Created by Sytten on 2017-10-14.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

protocol LoginController {
    func login(username: String, password: String, completion: @escaping (_ token: String, _ error: Error?) -> ())
    func register(username: String, password: String, completion: @escaping (_ error: Error?) -> ())
}
