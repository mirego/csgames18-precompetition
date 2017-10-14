//
//  CustomTabBarView.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-02-07.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class CustomTabBarView: UIView {
    static let height = 49.f

    let tabBarButtons: [UIButton]
    let postButton = PostButton()

    init() {
        tabBarButtons = [
            TabBarButton(iconName: "icnHome", label: "Home"),
            TabBarButton(iconName: "icnFriends", label: "Friends"),
            TabBarButton(iconName: "icnMessages", label: "Messages"),
            TabBarButton(iconName: "icnSettings", label: "Settings")]

        super.init(frame: .zero)

        backgroundColor = .white95

        tabBarButtons.forEach { addSubview($0) }

        addSubview(postButton)

        height = CustomTabBarView.height
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        postButton.setPosition(.positionTopHCenter, margins: .top(-7))

        let buttonSize = CGSize(width: (width - postButton.width) / 4, height: CustomTabBarView.height)
        var currentX = 0.f
        tabBarButtons.enumerated().forEach { (index, button) in
            button.setPosition(.positionTopLeft, margins: .left(currentX), size: buttonSize)
            currentX += button.width
            if index == 1 {
                currentX += postButton.width
            }
        }
    }

    override func hitTest(_ point: CGPoint, with event: UIEvent?) -> UIView? {
        if postButton.frame.contains(point) {
            return postButton
        }
        return super.hitTest(point, with: event)
    }
}
