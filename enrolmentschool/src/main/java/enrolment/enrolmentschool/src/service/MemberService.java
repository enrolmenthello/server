package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dto.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.dto.response.PostMemberResponse;

public interface MemberService {
    PostMemberResponse join(PostMemberJoinRequest postMemberJoinRequest);
}
