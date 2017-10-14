//
//  TabBarView.swift
//  XMedius
//
//  Created by Hugo Lefrancois on 2017-01-20.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit
import MRGTaylor

protocol TabBarViewDelegate: class {
    func didTapPostButton()
    func didSelectTab(atIndex index: Int)
}

class TabBarView: UIView {
    enum AnimationType {
        case none
        case fromLeft
        case fromRight
    }

    weak var delegate: TabBarViewDelegate?

    var bottomInset: CGFloat {
        return abs(tabBar.postButton.minY) + tabBar.height
    }

    var hiddenBottomHeight: CGFloat {
        return tabBar.height
    }

    private let tabBar = CustomTabBarView()

    private var mainView: UIView?

    private var lastSize: CGSize?

    init() {
        super.init(frame: .zero)

        backgroundColor = .silver

        tabBar.tabBarButtons.forEach { (button) in
            let tabIndex = tabBar.tabBarButtons.index(of: button)!
            button.touchUpInside(closure: { [weak self] (_) in
                self?.delegate?.didSelectTab(atIndex: tabIndex)
            })
        }

        addSubview(tabBar)
        tabBar.tabBarButtons[0].isSelected = true

        tabBar.postButton.touchUpInside { [weak self] (_) in
            self?.delegate?.didTapPostButton()
        }
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        if lastSize == nil || lastSize!.equalTo(CGSize.zero) || !lastSize!.equalTo(size) {
            lastSize = size

            layoutTabBar()
            layoutMainView()
        }
    }

    private func layoutTabBar() {
        var extraHeight: CGFloat = 0
        if #available(iOS 11.0, *) {
            extraHeight += safeAreaInsets.bottom
        }
        tabBar.setPosition(.positionBottomLeft, size: CGSize(width: width, height: CustomTabBarView.height + extraHeight))
    }

    private func layoutMainView() {
        mainView?.setPosition(.positionTopLeft, size: viewSize())
    }

    private func viewSize() -> CGSize {
        layoutIfNeeded()
        return size
    }

    func setMainView(_ mainView: UIView, animationType: AnimationType, completion: @escaping () -> (Void)) {
        let futurMainView = mainView
        let currentMainView = self.mainView

        insertSubview(futurMainView, belowSubview: tabBar)
        if let currentMainView = currentMainView, animationType != .none {
            futurMainView.alpha = 0
            futurMainView.setPosition(.positionTopLeft, margins: .left(viewSize().width / 6 * (animationType == .fromLeft ? -1 : 1)), size: viewSize())
            UIView.animate(withDuration: 0.2, delay: 0, options: .curveEaseOut, animations: {
                futurMainView.setPosition(.positionTopLeft)
                futurMainView.alpha = 1
                currentMainView.setPosition(.positionTopLeft, margins: .left(self.viewSize().width / 6 * (animationType == .fromLeft ? 1 : -1)))
            }, completion: { (_) in
                self.mainView = futurMainView
                currentMainView.removeFromSuperview()
                completion()
            })
        } else {
            currentMainView?.removeFromSuperview()
            self.mainView = futurMainView
            layoutMainView()
            completion()
        }
    }

    func setSelectedTab(index: Int) {
        for (buttonIndex, tabBarButton) in tabBar.tabBarButtons.enumerated() {
            tabBarButton.isSelected = buttonIndex == index
        }
    }
}
