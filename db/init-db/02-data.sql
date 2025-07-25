INSERT INTO holiday (`day`, `reason`, `type`, `created_at`, `created_by`)
VALUES
    ('Jan 1', 'New Year''s Day', 'FESTIVAL', CURRENT_DATE, 'DBA'),
    ('Mar 25', 'Greek Independence Day', 'FEDERAL', CURRENT_DATE, 'DBA'),
    ('Apr 17', 'Orthodox Easter Sunday', 'FESTIVAL', CURRENT_DATE, 'DBA'),
    ('May 1', 'Labour Day', 'FEDERAL', CURRENT_DATE, 'DBA'),
    ('Aug 15', 'Assumption of Mary', 'FESTIVAL', CURRENT_DATE, 'DBA'),
    ('Oct 28', 'Ohi Day', 'FEDERAL', CURRENT_DATE, 'DBA'),
    ('Dec 25', 'Christmas Day', 'FESTIVAL', CURRENT_DATE, 'DBA'),
    ('Dec 26', 'Synaxis of the Mother of God', 'FESTIVAL', CURRENT_DATE, 'DBA');

INSERT INTO role (`role_name`, `created_at`, `created_by`)
VALUES
    ('ADMIN', CURDATE(), 'DBA'),
    ('STUDENT', CURDATE(), 'DBA');

INSERT INTO user (`name`, `email`, `mobile_number`, `password`, `role_id`, `created_at`, `created_by`)
VALUES
    ('Admin', 'admin@eazyschool.com', '0123456789', 'admin', 1, CURDATE(), 'DBA');
