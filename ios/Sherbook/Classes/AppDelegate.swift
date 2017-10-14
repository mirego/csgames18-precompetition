//
//  AppDelegate.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.backgroundColor = .white
        window?.tintColor = .emerald
        window?.rootViewController = TabBarViewController(tabsViewControllers:
            [UINavigationController(rootViewController: HomeViewController(homeController: HomeControllerImpl())),
             UINavigationController(rootViewController: NotImplementedViewController(title: "Friends")),
             UINavigationController(rootViewController: NotImplementedViewController(title: "Messages")),
             UINavigationController(rootViewController: NotImplementedViewController(title: "Settings"))])
        window?.makeKeyAndVisible()

        return true
    }
}

