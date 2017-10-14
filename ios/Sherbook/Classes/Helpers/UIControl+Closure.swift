//
//  UIControl+Closure.swift
//  SAQ
//
//  Created by Hugo Lefrancois on 2016-11-04.
//  Copyright © 2016 Mirego. All rights reserved.
//

import UIKit

fileprivate var touchDownHandle: Int = 0
fileprivate var touchUpHandle: Int = 0
fileprivate var editingChangedHandle: Int = 0
fileprivate var valueChangedHandle: Int = 0

extension UIControl
{
    func touchUpInside(closure: @escaping (UIControl) -> Void)
    {
        let closureSelector = ClosureSelector<UIControl>(closure: closure)
        objc_setAssociatedObject(self, &touchUpHandle, closureSelector, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN_NONATOMIC)
        self.addTarget(closureSelector, action: closureSelector.selector, for: .touchUpInside)
    }

    func touchDown(closure: @escaping (UIControl) -> Void)
    {
        let closureSelector = ClosureSelector<UIControl>(closure: closure)
        objc_setAssociatedObject(self, &touchDownHandle, closureSelector, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN_NONATOMIC)
        self.addTarget(closureSelector, action: closureSelector.selector, for: .touchDown)
    }

    func editingChanged(closure: @escaping (UIControl) -> Void)
    {
        let closureSelector = ClosureSelector<UIControl>(closure: closure)
        objc_setAssociatedObject(self, &editingChangedHandle, closureSelector, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN_NONATOMIC)
        self.addTarget(closureSelector, action: closureSelector.selector, for: .editingChanged)
    }

    func valueChanged(closure: @escaping (UIControl) -> Void)
    {
        let closureSelector = ClosureSelector<UIControl>(closure: closure)
        objc_setAssociatedObject(self, &valueChangedHandle, closureSelector, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN_NONATOMIC)
        self.addTarget(closureSelector, action: closureSelector.selector, for: .valueChanged)
    }
}

fileprivate class ClosureSelector<Parameter>
{
    public let selector: Selector
    private let closure: (Parameter) -> (Void)

    init(closure: @escaping (Parameter) -> (Void))
    {
        self.selector = #selector(target(param:))
        self.closure = closure
    }

    // Unfortunately we need to cast to AnyObject here
    @objc func target(param: AnyObject)
    {
        closure(param as! Parameter)
    }
}
