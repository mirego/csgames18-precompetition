//
//  LoginViewController.swift
//  Sherbook
//
//  Created by Sytten on 2017-10-14.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

class LoginViewController: BaseViewController
{
    private let loginController: LoginController
    
    private var mainView: LoginView {
        return self.view as! LoginView
    }
    
    init(loginController: LoginController) {
        self.loginController = loginController
        super.init(nibName: nil, bundle: nil)
        title = "Login"
    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        view = LoginView()
        mainView.delegate = self
    }
}

extension LoginViewController: LoginViewDelegate
{
    func didTapLoginButton(username: String, password: String) {
        loginController.login(username: username, password: password) { [weak self] (user, error) in
            if error != nil {
                let alertController = UIAlertController(title: "", message: "An error occured", preferredStyle: .alert)
                alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
                self?.present(alertController, animated: true, completion: nil)
            } else {
                let homeController = TabBarViewController(tabsViewControllers:
                    [UINavigationController(rootViewController: HomeViewController(homeController: HomeControllerImpl())),
                     UINavigationController(rootViewController: NotImplementedViewController(title: "Friends")),
                     UINavigationController(rootViewController: NotImplementedViewController(title: "Messages")),
                     UINavigationController(rootViewController: NotImplementedViewController(title: "Settings"))])
                self?.present(homeController, animated:true, completion:nil)
            }
        }
    }
    
    func didTapRegisterButton(username: String, password: String) {
        loginController.register(username: username, password: password) { [weak self] (error) in
            if error != nil {
                let alertController = UIAlertController(title: "", message: "An error occured", preferredStyle: .alert)
                alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
                self?.present(alertController, animated: true, completion: nil)
            } else {
                let alertController = UIAlertController(title: "", message: "You can now login", preferredStyle: .alert)
                alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
                self?.present(alertController, animated: true, completion: nil)
            }
        }
    }
}
