//
//  QuestionID.h
//  GMAT
//
//  Created by Do Ngoc Trinh on 5/24/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>

@class QuestionPack;

NS_ASSUME_NONNULL_BEGIN

@interface QuestionID : NSManagedObject

// Insert code here to declare functionality of your managed object subclass
+ (instancetype)createAnswerWithStringID: (NSString *)stringID andQuestionPack:(QuestionPack *)questionPack;
@end

NS_ASSUME_NONNULL_END

#import "QuestionID+CoreDataProperties.h"
