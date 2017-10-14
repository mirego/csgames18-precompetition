//
//  RegisterButton.swift
//  Sherbook
//
//  Created by Sytten on 2017-10-14.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

class RegisterButton : UIButton
{
    override var isHighlighted: Bool {
        didSet {
            backgroundColor = isHighlighted ? .emeraldHighlighted : .emerald
        }
    }
    
    init() {
        super.init(frame: .zero)
        
        backgroundColor = .emerald
        
        setTitle("Register", for: UIControlState.normal)
        size = CGSize(width: 300, height: 63)
        layer.cornerRadius = height / 2
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
