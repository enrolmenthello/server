package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.response.PostMemberResponse;

public interface MemberService {
    PostMemberResponse join(PostMemberJoinRequest postMemberJoinRequest);
}
