//
//  LoginView.swift
//  Sherbook
//
//  Created by Sytten on 2017-10-14.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

protocol LoginViewDelegate: class {
    func didTapLoginButton(username: String, password: String)
    func didTapRegisterButton(username: String, password: String)
}

class LoginView: UIView
{
    private let loginButton = ContinueButton()
    private let registerButton = RegisterButton()
    private let usernameField = UITextField()
    private let passwordField = UITextField()
    private let logo = UIImageView(image: UIImage(named:"logoUdes"))
    
    weak var delegate: LoginViewDelegate?
    
    init()
    {
        super.init(frame: .zero)
        
        backgroundColor = .silver
        
        loginButton.touchUpInside { [weak self] (_) in
            self?.delegate?.didTapLoginButton(username: self?.usernameField.text ?? "", password: self?.passwordField.text ?? "")
        }
        
        registerButton.touchUpInside { [weak self] (_) in
            self?.delegate?.didTapRegisterButton(username: self?.usernameField.text ?? "", password: self?.passwordField.text ?? "")
        }
        
        addSubview(logo)
        addSubview(usernameField)
        addSubview(passwordField)
        addSubview(loginButton)
        addSubview(registerButton)
    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        logo.layer.cornerRadius = 50;
        logo.layer.masksToBounds = true;
        
        usernameField.placeholder = "Username"
        usernameField.font = UIFont(name: usernameField.font!.fontName, size: 25)
        usernameField.backgroundColor = .white
        usernameField.layer.cornerRadius = 10
        usernameField.layer.masksToBounds = true
        
        passwordField.placeholder = "Password"
        passwordField.font = UIFont(name: passwordField.font!.fontName, size: 25)
        passwordField.backgroundColor = .white
        passwordField.layer.cornerRadius = 10
        passwordField.layer.masksToBounds = true
        passwordField.isSecureTextEntry = true
        
        loginButton.setPosition(.positionBottomHCenter, margins: .bottom(30))
        registerButton.setRelativePosition(.relativePositionAboveCentered, toView: loginButton, margins: .bottom(20))
        passwordField.setRelativePosition(.relativePositionAboveCentered, toView: registerButton, margins: .bottom(70), size: CGSize(width: 300, height: 63))
        usernameField.setRelativePosition(.relativePositionAboveCentered, toView: passwordField, margins: .bottom(20), size: passwordField.size)
        logo.setPosition(.positionTopHCenter, margins: .top(50))
    }
}
