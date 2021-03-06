//
//  QuestionPack+CoreDataProperties.h
//  GMAT
//
//  Created by Do Ngoc Trinh on 5/24/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

#import "QuestionPack.h"

NS_ASSUME_NONNULL_BEGIN

@interface QuestionPack (CoreDataProperties)

@property (nullable, nonatomic, retain) NSString *packID;
@property (nullable, nonatomic, retain) NSString *available_time;
@property (nullable, nonatomic, retain) NSSet<QuestionID *> *questionIDs;

@end

@interface QuestionPack (CoreDataGeneratedAccessors)

- (void)addQuestionIDsObject:(QuestionID *)value;
- (void)removeQuestionIDsObject:(QuestionID *)value;
- (void)addQuestionIDs:(NSSet<QuestionID *> *)values;
- (void)removeQuestionIDs:(NSSet<QuestionID *> *)values;

@end

NS_ASSUME_NONNULL_END
